<?php 

header('Access-Control-Allow-Origin: *');
header('Content-type: application/json; charset=utf-8');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');

include('../../config/db_connection.php');

$data = json_decode(file_get_contents("php://input"));

$email = stripcslashes($data->email);

global $conn;

$status = 0;
$message = "";

if(empty($email)){
	$message = "Email address is required";
}else{
	$sql = "select * from user where email = '".$email."'";
	$result = mysqli_query($conn,$sql);
		
	if(mysqli_num_rows($result) === 1){
		$row = $result->fetch_assoc();
		$password = $row['password'];
		$full_name = $row['full_name'];

		$to = $email;
		$subject = 'Forgotten password';
		$txt = "Dear, ".$full_name.",\n\Your password is: ".$password.".\n\nEmobile Team.";

		mail($email, $subject, $txt);	

		$status = 1;
		$message = "Soon you will get email with your password";	
	}else{
		$message = "E-mail you entered is unavailable";
		
	}
}

$post_data = array(
	'status' => $status,
	'message' => $message
);

$post_data = json_encode($post_data);
echo $post_data;

mysqli_close($conn);

?>