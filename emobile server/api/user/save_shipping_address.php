<<<<<<< HEAD
<?php
 
header('Access-Control-Allow-Origin: *');
header('Content-type: application/json; charset=utf-8');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');
 
include('../../config/db_connection.php');
 
$data = json_decode(file_get_contents("php://input"));
 
global $conn;
 
$status = 0;
$message = "";
 
if(!isset($data->user_id) || !isset($data->city) || !isset($data->zip_code) || !isset($data->address) || !isset($data->phone)){
    $message = "All fields are required";
}else{
    $user_id = stripcslashes($data->user_id);
    $city = stripcslashes($data->city);
    $zip_code = stripcslashes($data->zip_code);
    $address = stripcslashes($data->address);
    $phone = stripcslashes($data->phone);
 
    $sql = "UPDATE shipping_address SET city = '$city', zip_code = $zip_code, address = '$address', phone = '$phone' WHERE user_id = $user_id";
    $conn->query($sql);
    $status = 1;
    $message = "Successfully updated address for salons";
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
 
global $conn;
 
$status = 0;
$message = "";
 
if(!isset($data->user_id) || !isset($data->city) || !isset($data->zip_code) || !isset($data->address) || !isset($data->phone)){
    $message = "All fields are required";
}else{
    $user_id = stripcslashes($data->user_id);
    $city = stripcslashes($data->city);
    $zip_code = stripcslashes($data->zip_code);
    $address = stripcslashes($data->address);
    $phone = stripcslashes($data->phone);
 
    $sql = "UPDATE shipping_address SET city = '$city', zip_code = $zip_code, address = '$address', phone = '$phone' WHERE user_id = $user_id";
    $conn->query($sql);
    $status = 1;
    $message = "Successfully updated address for salons";
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