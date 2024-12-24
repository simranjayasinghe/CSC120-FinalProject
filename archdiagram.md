Game
├── Player
│   ├── Inventory
│   │   └── Item (List of items)
│   ├── HealthManager
│   │   ├── manageHealth(int amount)
│   │   ├── decreaseHealth(int amount)
│   │   └── displayHealthStatus()
│   ├── getStatus() - Displays health and inventory information
│   └── getHealth() - Returns current health
├── GameMap
│   └── Location (Holds actions, items, and connections to other locations)
│       ├── addItem(Item)
│       ├── addAction(String)
│       ├── executeAction(String, Player, ProgressManager)
│       ├── displayItems()
│       └── getConnection(String)
├── ProgressManager
│   ├── advanceDay()
│   ├── isTimeUp()
│   ├── checkForKeyItems()
│   ├── hasMaterialsForHouse() - Checks if materials exist for building a house
│   ├── consumeMaterials() - Removes materials from inventory
│   └── markHouseBuilt()
├── Weather
│   ├── getCurrentWeather()
│   └── advanceTime() - Changes weather or time of day
├── CommandManager
│   ├── parse(String, Player)
│   └── execute(String, Player, GameMap, HealthManager, ProgressManager)
└── Dependencies
    ├── Player depends on Inventory and HealthManager
    ├── GameMap depends on Location
    ├── ProgressManager depends on Inventory and HealthManager
    ├── Location depends on Item and actions (Strings)
    ├── Game integrates all components
    └── CommandManager facilitates interactions