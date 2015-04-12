<?php
$con=mysqli_connect(45.55.151.194,"root","p3nis","tutr");
function createUser($uname, $fname, $lname, $email, $phone, school, $pass){
	$sql="insert into user 
		  (username, first_name, last_name, email, phone_number, school, password) 
		  values (".$uname.", ".$fname.", ".$lname.", ".$email.", ".$phone.", ".school.", ".$pass.");";
	if (mysqli_query($con,$sql))
	{
	   echo "Values have been inserted successfully";
	}
}

function makeTutor($tutor, $subject, $min_price){
	#flag user as a tutor
	$flagTutor = "update user
				  set isTutor = 1
				  where id = ".$tutor.";";
	if(!mysqli_query($con, $flagTutor)){
		echo "Error : There was a problem with making this user a tutor.";
		return;
	}
	#add tutor/subject to subjects table
	$addSubject = "insert into subjects
				   (tutor_id, subject, $min_price)
				   values(".$tutor.",'".$subject."',".$min_price.");";
	if (mysqli_query($con, $addSubject))
		echo "You are now a tutor for ".$subject.".";
}

function addReview($tutor, $subj, $comment, $rating){
	$insert = "insert into tutor_ratings
			  (tutor_id, subject, comment, rating)
			  values(".$tutor.",'".$subj."','".$comment."',".$rating");";
	if(!mysqli_query($con, $insert)){
		echo "Error : There was a problem while adding this review."
		return;
	}
	$getAvgs = "select avg(rating)
				from tutor_ratings
				where tutor_id = ".$tutor."
				  && (subj = NULL || subj = '".$subj."');";
	
}

function removeTutor($tutor, $subj){
	$sql = "delete from subjects
			where tutor_id = ".$tutor."
			  && subject = '".$subj."';";
	return mysqli_query($con, $sql));
}

?>