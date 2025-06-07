from flask import Blueprint, jsonify, render_template
from db import get_db_connection

items_bp = Blueprint('items', __name__)

@items_bp.route('/items')
def show_items():
    conn = get_db_connection()
    cursor = conn.cursor(dictionary=True)
    cursor.execute("SELECT * FROM items")
    items = cursor.fetchall()
    cursor.close()
    conn.close()
    return render_template("items.html", items=items)
