<?php
require 'dbConnect.php';
require 'sqlQueries.php';

$cmd = $_POST["command"];
switch ($cmd) {
	case "createUser":
		$uname = $_POST["uname"];
		$fname = $_POST["fname"];
		$lname = $_POST["lname"];
		$email = $_POST["email"];
		$phone = $_POST["phone"];
		$school = $_POST["school"];
		$pass = $_POST["pass"];
		$regID = $_POST["regID"];
		createUser($uname, $fname, $lname, $email, $phone, $school, $pass, $regID);
		break;

	case "makeTutor":
		$tutorID = $_POST["tutorID"];
		$subject = $_POST["subject"];
		$minPrice = $_POST["minPrice"];
		makeTutor($tutorID, $subject, $minPrice);
		break;

	case "addReview":
		$tutorID = $_POST["tutorID"];
		$subject = $_POST["subject"];
		$comment = $_POST["comment"];
		$rating = $_POST["rating"];
		addReview($tutorID, $subject, $comment, $rating);
		break;

	case "insertMessage":
		$sender = $_POST["sender"];
		$subject = $_POST["subject"];
		$text = $_POST["text"];
		$messageType = $_POST["messageType"];
		$price  = $_POST["price"];
		$ackedMessageID = $_POST["ackedMessageID"];
		insertMessage($sender, $subject, $text, $messageType, $price, $ackedMessageID);
		break;

	case "getOpenMessages":
		$userID = $_POST["userID"];
		getOpenMessages($userID);
		break;

	case "findTutors":
		$course	 = $_POST["course"];
		$price = $_POST["price"];
		findTutors($course, $price)
		break;

	case "sendMessage":
		$reciever = $_POST["reciever"];
		$message = $_POST["message"];
		sendMessage($reciever, $message);
		break;

	case "makeBroadcastMessages":
		$student = $_POST["student"];
		$course = $_POST["course"];
		$price = $_POST["price"];
		$text = $_POST["text"];
		makeBroadcastMsgs($student, $course, $price, $text);
		break;

	case "createRequest":
		$studentID = $_POST["studentID"];
		$tutorID = $_POST["tutorID"];
		$subject = $_POST["subject"];
		$price = $_POST["price"];
		createRequest($studentID, $tutorID, $subject, $price);
		break;

	case "getRequests":
		$userID = $_POST["userID"];
		$sortBy = $_POST["sortBy"];
		getRequests($userID, $sortBy);
		break;

	case "getTutorRatings":
		$tutorID = $_POST["tutorID"];
		$subject = $_POST["subject"];
		getTutorRatings($tutorID, $subject);
		break;

}

?>
