
## 1 
bierce = {
    "day": "A period of twenty-four hours, mostly misspent",
    "positive": "Mistaken at the top of one's voice",
    "misfortune": "The kind of fortune that never misses",
}
print(bierce) 

## 2 
Week = {
    "Monday": "First day of the work week" 
}
print(Week)

## 3&4 
Day = {
    "Morning": "Journal, Coffee, Snack",
    "Lunch": "Take a break, Eat lunch",
    "Afternoon": "Gym",
    "Night": "Dinner, Get ready for next day"
}
print(Day)

##5 
Morning = dict(Journal="Gratitude", Coffee="No cream or sugar", Snack="Fruit" )
print(Morning)

##6
Day["Afternoon"] = "Gym, Shower"
print(Day)

##7 
Day["Morning"]
print(Morning)

##8
print(Day.keys())