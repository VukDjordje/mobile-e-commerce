<<<<<<< HEAD
<?php
 
header('Access-Control-Allow-Origin: *');
header('Content-type: application/json; charset=utf-8');
header('Access-Control-Allow-Methods: GET');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');
 
include('../../config/db_connection.php');
 
if(isset($_GET['prod_id'])){
    $prod_id = $_GET['prod_id'];
}else{
    http_response_code(400);
    die();
}
 
global $conn;
 
$status = 0;
$message = "";
 
$sql = "SELECT c.*, u.full_name FROM commen1 c join user u on c.user_id = u.user_id WHERE c.product_id = $prod_id";
$results = $conn->query($sql);
 
$post_data = array();
 
if(mysqli_num_rows($results)>0){
 
    // products array
    $post_data["data"]=array();
    $status = 1;
    $message = "Successfully found comments";
 
    while ($comment = mysqli_fetch_assoc($results)){
        $comment_item=array(
            "comment_id" => $comment['comment_id'],
            "full_name" => $comment['full_name'],
            "rating" => $comment['rating'],
            "comment" => $comment['comment']
        );
 
        array_push($post_data["data"], $comment_item);
    }
}else{
    $message = "We did't find any comments for this product!";
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
header('Access-Control-Allow-Methods: GET');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');
 
include('../../config/db_connection.php');
 
if(isset($_GET['prod_id'])){
    $prod_id = $_GET['prod_id'];
}else{
    http_response_code(400);
    die();
}
 
global $conn;
 
$status = 0;
$message = "";
 
$sql = "SELECT c.*, u.full_name FROM commen1 c join user u on c.user_id = u.user_id WHERE c.product_id = $prod_id";
$results = $conn->query($sql);
 
$post_data = array();
 
if(mysqli_num_rows($results)>0){
 
    // products array
    $post_data["data"]=array();
    $status = 1;
    $message = "Successfully found comments";
 
    while ($comment = mysqli_fetch_assoc($results)){
        $comment_item=array(
            "comment_id" => $comment['comment_id'],
            "full_name" => $comment['full_name'],
            "rating" => $comment['rating'],
            "comment" => $comment['comment']
        );
 
        array_push($post_data["data"], $comment_item);
    }
}else{
    $message = "We did't find any comments for this product!";
}
 
$post_data['status'] = $status;
$post_data['message'] = $message;
 
 
$post_data = json_encode($post_data);
echo $post_data;
 
mysqli_close($conn);
 
>>>>>>> 709756eda9aab0678399c2cfa2229241dccc7dcd
?>