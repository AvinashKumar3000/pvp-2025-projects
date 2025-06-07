from flask import Flask, render_template
from routes.usersRoutes import users_bp
from routes.itemsRoutes import items_bp
from routes.cartRoutes import cart_bp

app = Flask(__name__)
app.secret_key = 'your_secret_key_here'  # Required for session management

# Blueprints
app.register_blueprint(users_bp)
app.register_blueprint(items_bp)
app.register_blueprint(cart_bp)

# Root route
@app.route('/')
def index():
    return render_template("index.html")

if __name__ == '__main__':
    app.run(debug=True)
