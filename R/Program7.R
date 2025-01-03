items <- data.frame(
    ItemCode = c(1001:1005),
    ItemCategory = c("Electronice", "Desktop Supplies", "Office Supplies", "USB", "CD Drive"),
    ItemPrice = c(7, 3, 3.5, 4, 8) * 100 
)

print(items[items$ItemPrice >= 350, ])

print(items[items$ItemCategory %in% c("Desktop Supplies", "Office Supplies"), ])

item.details <- data.frame(
    ItemCode = items$ItemCode,
    ItemQtyOnHand = rep(10, 5),
    ItemReorderLvl = c(5, 3, 2, 6, 7)
)

merged.items <- merge(items, item.details, by = "ItemCode")

print(merged.items)