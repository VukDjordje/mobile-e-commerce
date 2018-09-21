<?php
 
header('Access-Control-Allow-Origin: *');
header('Content-type: application/json; charset=utf-8');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');
 
include('../../config/db_connection.php');
 
$data = json_decode(file_get_contents("php://input"));
 
$user_id = stripcslashes($data->user_id);
$product_id = stripcslashes($data->product_id);
$rating = stripcslashes($data->rating);
$comment = stripcslashes($data->comment);
 
global $conn;
 
$status = 0;
$message = "";
 
if(empty($user_id) || empty($product_id) || empty($rating) || empty($comment)){
    $message = "All fields are required";
}else{ 
    $sql1 = "SELECT * FROM commen1 WHERE user_id = $user_id and product_id = $product_id";
    $results = $conn->query($sql1);
    if(mysqli_num_rows($results)>0){
        $message = "You have already rated this product";
    }else{
        $sql2 = "insert into commen1 (user_id, product_id, rating, comment) values($user_id, $product_id, $rating, '$comment')";
        $result = mysqli_query($conn,$sql2);
        $message = "You have successfully evaluated the product";
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