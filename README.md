# CSC120-FinalProject

## Deliverables:
 - Your final codebase
 - Your revised annotated architecture diagram
 - Design justification (including a brief discussion of at least one alternative you considered)
 - A map of your game's layout (if applicable)
 - `cheatsheet.md`
 - Completed `rubric.md`
  
## Additional Reflection Questions
 - What was your **overall approach** to tackling this project?
 - What **new thing(s)** did you learn / figure out in completing this project?
 - Is there anything that you wish you had **implemented differently**?
 - If you had **unlimited time**, what additional features would you implement?
 - What was the most helpful **piece of feedback** you received while working on your project? Who gave it to you?
 - If you could go back in time and give your past self some **advice** about this project, what hints would you give?
 - _If you worked with a team:_ please comment on how your **team dynamics** influenced your experience working on this project.

My overall approach to this project was to break down the requirements into manageable pieces and implement them incrementally. I started by focusing on the core structure, including creating the Player, Inventory, and Location classes, and then moved on to implementing game mechanics like movement, item collection, and managing player health. I frequently tested each feature after adding it to ensure that everything was functioning as expected, which helped me catch and resolve issues early. To handle more complex functionality, such as tracking progress and enabling restricted access based on collected items, I leveraged a ProgressManager to centralize these responsibilities. I also tried to keep my code modular, which allowed me to refine or add features without breaking existing functionality.

I definitely had many more ideas (challenges that were dependent on day/location or whether or not action was taken) but personall did not have as much time as I would have wanted to fully develop the game as much as I would like. If I had unlimited time, I would work on more dynamic elements in the game, as well as many having a skill progression tracker, and just making the game a little more interactive overall.

One thing I wish I had implemented differently was the initial approach to handling user commands and restricted access paths. Early on, I didn’t account for multi-word commands, which made the gameplay less intuitive. Revisiting and refining this part of the code took more time than I expected, and planning for more flexible input handling from the start would have been beneficial. I also could have designed the Location class to support actions more efficiently, maybe  by using an Action object or a more generalized system for location-specific interactions. This would have made adding new features like challenges or unique tasks at each location much simpler.

I designed this project focusing on separating responsibilities by class. By separating everything into distinct classes like Player, Inventory, ProgressManager, and HealthManager, we ensured that each class had a clear responsibility. This made the code easier to test and expand. An alternative design could have focused more on event-driven design. I could have used a system where each user action triggers an event. Events would then be processed by a central class, which could then call the other needed classes (e.g., Location, Player, or ProgressManager). This approach would have made it easier to track and modify the flow of actions across the game, especially for more complex scenarios, and probably would have helped me make the program more interactive.