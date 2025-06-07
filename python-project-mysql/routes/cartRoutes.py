from flask import Blueprint, request, render_template, redirect, url_for
from db import get_db_connection

cart_bp = Blueprint('cart', __name__)

@cart_bp.route('/cart/<int:user_id>', methods=['GET'])
def show_cart(user_id):
    conn = get_db_connection()
    cursor = conn.cursor(dictionary=True)

    cursor.execute("""
        SELECT cart.id, items.name AS item_name, items.price, cart.quantity
        FROM cart
        JOIN items ON cart.item_id = items.id
        WHERE cart.user_id = %s
    """, (user_id,))
    
    cart_items = cursor.fetchall()
    cursor.close()
    conn.close()

    return render_template("cart.html", cart=cart_items, user_id=user_id)


@cart_bp.route('/cart/<int:user_id>/add', methods=['GET', 'POST'])
def add_to_cart(user_id):
    conn = get_db_connection()
    cursor = conn.cursor(dictionary=True)

    if request.method == 'POST':
        item_id = request.form.get('item_id')
        quantity = request.form.get('quantity')

        cursor.execute(
            "INSERT INTO cart (user_id, item_id, quantity) VALUES (%s, %s, %s)",
            (user_id, item_id, quantity)
        )
        conn.commit()
        cursor.close()
        conn.close()

        return redirect(url_for('cart.show_cart', user_id=user_id))

    # If GET, show available items to add
    cursor.execute("SELECT * FROM items")
    items = cursor.fetchall()
    cursor.close()
    conn.close()

    return render_template("add_to_cart.html", items=items, user_id=user_id)
