<?php

include 'sqlQueries.php';

#getUserID("jtg");

#makeTutor(1, "AMS 161", 3.20);

#addReview(2, "CSE 160", NULL, 5);

makeBroadcastMsgs(2, "AMS 161", 4.00, "how to math?");

#createRequest(1, 2, "cse 330", 3);
echo "----open messages----\n";
getOpenMessages(2);
echo "----ratings----\n";
getTutorRatings(2, NULL);
echo "----requests----\n";
getRequests(2, NULL);
?>
