<?php 

header('Access-Control-Allow-Origin: *');
header('Content-type: application/json; charset=utf-8');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');

include('../../config/db_connection.php');

$data = json_decode(file_get_contents("php://input"));

$user_id = stripcslashes($data->user_id);
$product_id = stripcslashes($data->product_id);

global $conn;

$status = 0;
$message = "";

if(empty($user_id) || empty($product_id)){
	$message = "An error has occurred";
}else{	
    $sql1 = "SELECT * FROM cart WHERE user_id =".$user_id." and product_id =".$product_id." and ordered = 0";
    $results = $conn->query($sql1);
    if(mysqli_num_rows($results)>0){
        $message = "This product is already in cart!";
    }else{
        $sql2 = "insert into cart (user_id, product_id, ordered) values(".$user_id.",".$product_id.", 0)";
    	$result = mysqli_query($conn,$sql2);
    	$message = "Successfully add to Cart";
    	$status = 1;
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