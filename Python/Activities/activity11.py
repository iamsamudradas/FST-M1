fruit_shop = {
    "apple": 10,
    "banana": 15,
    "orange": 8,
    "peaches": 15
}

key_to_check = input("What are you looking for? ").lower()

if key_to_check in fruit_shop:
    price = fruit_shop[key_to_check]
    print(f"Yes, {key_to_check} is available and its price is {price} rupees.")
else:
    print("No, this is not available")