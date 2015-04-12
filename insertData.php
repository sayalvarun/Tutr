<?php
include 'sqlQueries.php';

createUser("ScoobyDoo", "Scooby", "Doo", "scoobydooooo@gmail.com", "4560938934", "UMD", "scoobysnacks", "1");
$t1 = getUserID("ScoobyDoo");
makeTutor($t1, "Math", 5);

insertMessage(21, "Math", "How to math? Willing to pay in food as well", 0, 10, NULL);
insertMessage(21, "Compter Science", "How to Java? :(", 0, 10, NULL);





?>