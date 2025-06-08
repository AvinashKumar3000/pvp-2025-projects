from flask import Blueprint, render_template, session, request, redirect, url_for
from db import get_db_connection
from collections import defaultdict

items_bp = Blueprint('items', __name__)

@items_bp.route('/items')
def show_items():
    conn = get_db_connection()
    cursor = conn.cursor(dictionary=True)

    cursor.execute("SELECT * FROM items")
    items = cursor.fetchall()

    # Group items by category
    grouped_items = defaultdict(list)
    for item in items:
        grouped_items[item['category']].append(item)

    item_categories = {}
    for k in grouped_items.keys():
        item_categories[k] = len(grouped_items[k])

    cursor.close()
    conn.close()

    return render_template("items.html", grouped_items=grouped_items, item_categories=item_categories)


# Predefined categories list (could come from DB as well)
CATEGORIES = [
    'Fruits', 
    'Vegetables', 
    'Dairy', 
    'Beverages', 
    'Snacks',
    "Books",
    "Grocery",
    "Fashion",
    "Electronics",
]

@items_bp.route('/items/add', methods=['GET', 'POST'])
def add_item():
    if request.method == 'POST':
        name = request.form['name']
        about = request.form.get('about', '')
        category = request.form['category']
        price = request.form['price']
        stock = request.form['stock']
        image_url = request.form.get('image_url', '')

        conn = get_db_connection()
        cursor = conn.cursor()
        cursor.execute(
            "INSERT INTO items (name, about, category, price, stock, image_url) VALUES (%s, %s, %s, %s, %s, %s)",
            (name, about, category, price, stock, image_url)
        )
        conn.commit()
        cursor.close()
        conn.close()

        return redirect(url_for('index'))  # or wherever you want to redirect after adding

    # GET request - show form with categories
    return render_template('add_to_item.html', categories=CATEGORIES)