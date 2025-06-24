from flask import Blueprint

shop_bp = Blueprint("shop", __name__)

db = {
    "chocolate": ["🍫 diary milk","🍫 kit kat"],
    "sweet":["🍬 sweet candy","🍭 candy stick"],
    "snacks":["🍟 french fries","🍗 leg piece","🍳 half boil"]
}

@shop_bp.route("/shop/<item>/<id>")
def getItems(item,id):
    id = int(id)
    if item not in db.keys():
        return "sry! 🥲 your item is not here."
    if id not in range(0,len(db[item])):
        return "sry! 🥲 search for wrong item."
    return db[item][id]
