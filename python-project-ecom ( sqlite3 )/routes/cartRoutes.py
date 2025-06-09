from flask import Blueprint, request, render_template, redirect, url_for
from db import get_db_connection
import sqlite3

cart_bp = Blueprint('cart', __name__)

# Helper to fetch DB connection and cursor
def get_cursor():
    conn = get_db_connection()
    conn.row_factory = sqlite3.Row  # Enables dictionary-like access
    return conn, conn.cursor()

# ✅ Show Cart
@cart_bp.route('/cart/<int:user_id>', methods=['GET'])
def show_cart(user_id):
    conn, cursor = get_cursor()

    cursor.execute("""
        SELECT cart.id, items.id as item_id, items.name AS item_name, items.price, 
               cart.quantity, items.image_url, items.about
        FROM cart
        JOIN items ON cart.item_id = items.id
        WHERE cart.user_id = ?
    """, (user_id,))
    
    cart_items = cursor.fetchall()
    cursor.close()
    conn.close()

    return render_template("cart.html", cart=cart_items, user_id=user_id)

# ✅ Add to Cart (GET & POST)
@cart_bp.route('/cart/<int:user_id>/add', methods=['GET', 'POST'])
def add_to_cart(user_id):
    conn, cursor = get_cursor()

    if request.method == 'POST':
        item_id = request.form.get('item_id')
        quantity = int(request.form.get('quantity'))

        # Check if item already exists in cart
        cursor.execute(
            "SELECT id FROM cart WHERE user_id = ? AND item_id = ?",
            (user_id, item_id)
        )
        existing = cursor.fetchone()

        if existing:
            # Update quantity instead of inserting again
            cursor.execute(
                "UPDATE cart SET quantity = quantity + ? WHERE id = ?",
                (quantity, existing['id'])
            )
        else:
            cursor.execute(
                "INSERT INTO cart (user_id, item_id, quantity) VALUES (?, ?, ?)",
                (user_id, item_id, quantity)
            )

        conn.commit()
        cursor.close()
        conn.close()
        return redirect(url_for('cart.show_cart', user_id=user_id))

    # GET request — show available items
    cursor.execute("SELECT * FROM items")
    items = cursor.fetchall()
    cursor.close()
    conn.close()

    return render_template("add_to_cart.html", items=items, user_id=user_id)

# ✅ Delete Item from Cart
@cart_bp.route('/cart/<int:user_id>/delete/<int:cart_id>', methods=['POST'])
def delete_from_cart(user_id, cart_id):
    conn, cursor = get_cursor()
    cursor.execute("DELETE FROM cart WHERE id = ? AND user_id = ?", (cart_id, user_id))
    conn.commit()
    cursor.close()
    conn.close()

    return redirect(url_for('cart.show_cart', user_id=user_id))
