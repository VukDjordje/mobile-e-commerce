<<<<<<< HEAD
<?php 

header('Access-Control-Allow-Origin: *');
header('Content-type: application/json; charset=utf-8');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');

include('../../config/db_connection.php');

$data = json_decode(file_get_contents("php://input"));

$order_id = stripcslashes($data->order_id);

global $conn;

$status = 0;
$message = "";

if(empty($order_id)){
	$message = "An error has occurred";
}else{
	$sql = "UPDATE orders SET show_order = 0 WHERE order_id =".$order_id;
	$conn->query($sql);
	$message = "Successfully deleted from purchase history";
	$status = 1;
}

$post_data['status'] = $status;
$post_data['message'] = $message;

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

$order_id = stripcslashes($data->order_id);

global $conn;

$status = 0;
$message = "";

if(empty($order_id)){
	$message = "An error has occurred";
}else{
	$sql = "UPDATE orders SET show_order = 0 WHERE order_id =".$order_id;
	$conn->query($sql);
	$message = "Successfully deleted from purchase history";
	$status = 1;
}

$post_data['status'] = $status;
$post_data['message'] = $message;

$post_data = json_encode($post_data);
echo $post_data;

mysqli_close($conn);

>>>>>>> 709756eda9aab0678399c2cfa2229241dccc7dcd
?>