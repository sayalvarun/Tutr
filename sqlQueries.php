<?php
conn = connect();

function createUser($uname, $fname, $lname, $email, $phone, $school, $pass, $regID){
	$sql="insert into user 
		 (username, first_name, last_name, email, phone_number, school, password, registration_key)
		  values ('$uname', '$fname', '$lname', '$email', '$phone', '$school', '$pass', '$regID');";
	if (mysqli_query($con,$sql))
	   echo "Values have been inserted successfully";
}

function makeTutor($tutor, $subject, $min_price){
	#flag user as a tutor
	$flagTutor = "update user
				  set isTutor = 1
				  where id = '$tutor';";
	if(!mysqli_query($con, $flagTutor)){
		echo "Error : There was a problem with making this user a tutor.";
		return;
	}
	#add tutor/subject to subjects table
	$addSubject = "insert into subjects
				   (tutor_id, subject, min_price)
				   values('$tutor','$subject','$min_price');";
	if (mysqli_query($con, $addSubject))
		echo "You are now a tutor for $subject";
}

function addReview($tutor, $subj, $comment, $rating){
	$insert = "insert into tutor_ratings
			  (tutor_id, subject, comment, rating)
			  values('$tutor','$subj','$comment','$rating');";
	if(!mysqli_query($con, $insert)){
		echo "Error : There was a problem while adding this review.";
		return;
	}
	$getAvgs = "select avg(rating)
				from tutor_ratings
				where tutor_id = '$tutor'
				  && (subj = NULL || subj = '$subj');";
	$result = mysqli_query($con, $getAvgs);
	
	if(mysqli_num_rows($result) < 1){
		echo "Error : There was a problem while coming up with an average rating.";
		return;
	}
	$row = mysqli_fetch_array($result);
	if($row[0] < 1.5){
		if(removeTutor($tutor, $subj))
			echo "Tutor removed successfully.";
	}
}

function removeTutor($tutor, $subj){
	$sql = "delete from subjects
			where tutor_id = '$tutor'
			  && subject = '$subj';";
	return mysqli_query($con, $sql);
}

#inserts entry into message table
function insertMessage($sender, $subject, $text, $msgType, $price, $prevMsg){
	$sql = "insert into message
			(sender_id, subject, text, msg_type, price, acked_msg_id)
			values($sender, $subject, $text, $msgType, $price, $prevMsg);";
	mysqli_query($con, $sql);
}

function getMostRecentMessage(){
	$sql = "select max(id)
			from message;";
	$res = mysqli_query($con, $sql);
	if(mysqli_num_rows($res) < 1){
		echo "Error : This shouldn't happen...";
		return;
	}
	$row = mysqli_fetch_array($res);
	return $row[0];
}

#finds all tutors for a course who are willing to tutor for the price
function findTutors($course, $price){
	$getTutors = "select * from subjects
				  where subject = '$course'
				    && $price > min_price;";
	return mysqli_query($con, $getTutors);
}

#inserts entry into msg_recevier table - receiver to msgs
function sendMsg($receiver, $msg){
	$sql = "insert into msg_receivers
			100)(receiver_id, msg_id)
			values($receiver, $msg);";
	return mysqli_query($con, $sql);
}

function makeBroadcastMsgs($student, $course, $price, $text){
	#find all tutors of a course
	$tResults = findTutors($course);
	#if there were tutors
	if(mysqli_num_rows($tResults) > 0){
		#send broadcast to all (message table)
		insertMessage($student, $course, $text, 0, NULL, NULL);
		$msg = getMostRecentMsg();
		while($row = mysqli_fetch_assoc($tResults)){
			#fill in receiver field for msgs - msg_receivers
			sendMsg($row["tutor_id"], $msg);
		}
	}
	else
		echo "Sorry, there are no tutors for that course at this time.";
}

function createRequest($student, $tutor, $subject, $price){
	$sql = "insert into request
			(student_id, tutor_id, subject, price)
			values($student, $tutor, '$subject', $price);";
	if(!mysqli_query($con, $sql))
		echo ("Error : There was a problem in making this request.");
}

function getOpenMessages($user){
	#get all open messages where the user is the sender
	$senderMsgs = "select * from message
				   where sender_id = $user
				     && msg_type > 2;";
	$sResults = mysqli_query($con, $senderMsgs);
	#print out the results
	if(mysqli_num_rows($tResults) > 0){
		while($row = mysqli_fetch_assoc($tResults)){
			echo "$row[subject] - $row[text]";
		}
	}
	
	#get all open messages where the user is the receiver
	$receiverMsgs = "select * from message m join msg_receivers r
					where r.receiver_id = $user
					  && m.id = r.msg_id
					  && m.msg_type > 2;";
	$rResults = mysqli_query($con, $receiverMsgs);
	#print out the results
	if(mysqli_num_rows($rResults) > 0){
		while($row = mysqli_fetch_assoc($rResults)){
			echo "$row[subject] - $row[text]";
		}
	}
}

function getRequests($user, $sortBy){
	$requests = "select * from requests
				where tutor_id = $user ||
				  student_id = $user
				order by ";
	if(!$sortBy)
		$requests = $requests."id;";
	else
		$requests = $requests."$sortBy;";
	
	$res = mysqli_query($con, $requests);
	if(mysqli_num_rows($rResults) > 0){
		while($row = mysqli_fetch_assoc($res)){
			echo "$row[student_id] <- $row[tutor_id] + $row[subject]";
		}
	}
}

function getTutorRatings($tutor, $subject){
	$ratings = "select * from tutor_ratings
				where tutor_id = $tutor ";
	if($subject)
		$ratings = $ratings."&& subject = $subject";
	$ratings = $ratings.";";
	
	$res = mysqli_query($con, $ratings);
	
	if(mysqli_num_rows($res) > 0){
		while($row = mysqli_fetch_assoc($res)){
			echo "$row[rating] - $row[comment]"
		}
	}
}
?>
