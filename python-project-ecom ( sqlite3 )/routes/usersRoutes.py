from flask import Blueprint, request, render_template, redirect, url_for, session
from db import get_db_connection
import sqlite3

users_bp = Blueprint('users', __name__)

@users_bp.route('/register', methods=['GET', 'POST'])
def register():
    if request.method == 'POST':
        data = request.form
        conn = get_db_connection()
        cursor = conn.cursor()
        cursor.execute(
            "INSERT INTO users (username, email, password) VALUES (?, ?, ?)",
            (data['username'], data['email'], data['password'])
        )
        conn.commit()
        cursor.close()
        conn.close()
        return redirect(url_for('index'))

    return render_template("register.html")


@users_bp.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        email = request.form['email']
        password = request.form['password']

        conn = get_db_connection()
        conn.row_factory = sqlite3.Row  # Enables dictionary-style access
        cursor = conn.cursor()
        cursor.execute("SELECT * FROM users WHERE email = ? AND password = ?", (email, password))
        user = cursor.fetchone()
        cursor.close()
        conn.close()

        if user:
            session['user_id'] = user['id']
            session['username'] = user['username']
            return redirect(url_for('index'))  # Adjust as needed
        else:
            return render_template("login.html", error="Invalid email or password.")

    return render_template('login.html')


@users_bp.route('/logout')
def logout():
    session.clear()
    return redirect(url_for('index'))
