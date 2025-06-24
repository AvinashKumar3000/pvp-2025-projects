from flask import Blueprint, render_template, session, request, redirect, url_for
from db import get_db_connection
from collections import defaultdict
import sqlite3

items_bp = Blueprint('items', __name__)

@items_bp.route('/items')
def show_items():
    conn = get_db_connection()
    conn.row_factory = sqlite3.Row
    cursor = conn.cursor()

    cursor.execute("SELECT * FROM songs")
    songs = cursor.fetchall()

    # Group songs by genre
    grouped_songs = defaultdict(list)
    for song in songs:
        grouped_songs[song['genre']].append(song)

    # Count songs in each genre
    genre_counts = {genre: len(group) for genre, group in grouped_songs.items()}

    cursor.close()
    conn.close()

    return render_template(
        "items.html",  # Make sure your template file is renamed to match
        grouped_songs=grouped_songs,
        genre_counts=genre_counts
    )

@items_bp.route('/items/add', methods=['GET', 'POST'])
def add_song():
    if request.method == 'POST':
        title = request.form['title']
        artist = request.form['artist']
        album = request.form.get('album', '')
        genre = request.form['genre']
        release_year = request.form['release_year']
        cover_url = request.form.get('cover_url', '')

        conn = get_db_connection()
        cursor = conn.cursor()
        cursor.execute(
            "INSERT INTO songs (title, artist, album, genre, release_year, cover_url) VALUES (?, ?, ?, ?, ?, ?)",
            (title, artist, album, genre, release_year, cover_url)
        )
        conn.commit()
        cursor.close()
        conn.close()

        return redirect(url_for('index'))  # adjust to your home route

    return render_template('add_to_item.html')
