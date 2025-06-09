# db.py
import sqlite3

def get_db_connection():
    conn = sqlite3.connect('ecommerce.db')  # This will create the file if it doesn't exist
    conn.row_factory = sqlite3.Row         # Optional: return rows as dictionaries
    return conn