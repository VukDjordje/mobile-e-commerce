<<<<<<< HEAD
<?php 

header('Access-Control-Allow-Origin: *');
header('Content-type: application/json; charset=utf-8');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');

include('../../config/db_connection.php');

$data = json_decode(file_get_contents("php://input"));

$user_id = stripcslashes($data->user_id);
$comment = stripcslashes($data->comment);
$rating_scale = stripcslashes($data->rating_scale);

global $conn;

$status = 0;
$message = "";

if(empty($user_id) || empty($comment)){
	$message = "Please fill in feedback text box!";
} else {
    	$sql = "insert into comment(user_id,comment,rating_scale) VALUES(".$user_id.",'".$comment."','".$rating_scale."')";
    	$conn->query($sql);
    	
    	$message = "Thank you for sharing your feedback";
		$status = 1;
}

$post_data = array(
	'status' => $status,
	'message' => $message
);

$post_data = json_encode($post_data);
echo $post_data;

mysqli_close($conn);

=======
<?php 

header('Access-Control-Allow-Origin: *');
header('Content-type: application/json; charset=utf-8');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');

include('../../config/db_connection.php');

$data = json_decode(file_get_contents("php://input"));

$user_id = stripcslashes($data->user_id);
$comment = stripcslashes($data->comment);
$rating_scale = stripcslashes($data->rating_scale);

global $conn;

$status = 0;
$message = "";

if(empty($user_id) || empty($comment)){
	$message = "Please fill in feedback text box!";
} else {
    	$sql = "insert into comment(user_id,comment,rating_scale) VALUES(".$user_id.",'".$comment."','".$rating_scale."')";
    	$conn->query($sql);
    	
    	$message = "Thank you for sharing your feedback";
		$status = 1;
}

$post_data = array(
	'status' => $status,
	'message' => $message
);

$post_data = json_encode($post_data);
echo $post_data;

mysqli_close($conn);

>>>>>>> 709756eda9aab0678399c2cfa2229241dccc7dcd
?>