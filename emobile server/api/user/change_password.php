<<<<<<< HEAD
<?php
 
header('Access-Control-Allow-Origin: *');
header('Content-type: application/json; charset=utf-8');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');
 
include('../../config/db_connection.php');
 
$data = json_decode(file_get_contents("php://input"));
 
$user_id = stripcslashes($data->user_id);
$current_password = stripcslashes($data->current_password);
$new_password = stripcslashes($data->new_password);
 
global $conn;
 
$status = 0;
$message = "";
 
if(empty($user_id) || empty($current_password) || empty($new_password)){
    $message = "All fields are required";
}else{
    $sql = "select * from user where user_id = $user_id";
    $result = mysqli_query($conn,$sql);
    $user_item = mysqli_fetch_assoc($result);
    $password = $user_item['password'];
 
    if(strcmp($password, $current_password) == 0){
        if(strcmp($password, $new_password) == 0){
            $message = "The new password can not be the same as the old one";
        }else{
            $sql = "UPDATE user SET password = '$new_password' WHERE user_id = $user_id";
            mysqli_query($conn,$sql);
            $status = 1;
            $message = "Successfully changed password, you must log in again with a new password";
        }
    }else{
        $message = "Your current password is incorrect";
    }  
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
$current_password = stripcslashes($data->current_password);
$new_password = stripcslashes($data->new_password);
 
global $conn;
 
$status = 0;
$message = "";
 
if(empty($user_id) || empty($current_password) || empty($new_password)){
    $message = "All fields are required";
}else{
    $sql = "select * from user where user_id = $user_id";
    $result = mysqli_query($conn,$sql);
    $user_item = mysqli_fetch_assoc($result);
    $password = $user_item['password'];
 
    if(strcmp($password, $current_password) == 0){
        if(strcmp($password, $new_password) == 0){
            $message = "The new password can not be the same as the old one";
        }else{
            $sql = "UPDATE user SET password = '$new_password' WHERE user_id = $user_id";
            mysqli_query($conn,$sql);
            $status = 1;
            $message = "Successfully changed password, you must log in again with a new password";
        }
    }else{
        $message = "Your current password is incorrect";
    }  
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