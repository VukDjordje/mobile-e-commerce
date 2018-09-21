<?php 

header('Access-Control-Allow-Origin: *');
header('Content-type: application/json; charset=utf-8');
header('Access-Control-Allow-Methods: GET');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');

include('../../config/db_connection.php');

if(isset($_GET['user_id'])){
    $user_id = $_GET['user_id'];
}else{
    http_response_code(400);
    die();
}

global $conn;

$status = 0;
$message = "";

$sql = "SELECT o.*, p.title, p.image FROM orders o join cart c on o.cart_id = c.cart_id join product p on c.product_id = p.product_id WHERE o.show_order = 1 and c.user_id =".$user_id;
$results = $conn->query($sql);
 
$post_data = array();

if(mysqli_num_rows($results)>0){
 
    // products array
    $post_data["data"]=array();
    $status = 1;
    $message = "Successfully found orders";
 
    while ($orders = mysqli_fetch_assoc($results)){
        $order_item=array(
            "order_id" => $orders['order_id'],
            "cart_id" => $orders['cart_id'],
            "city" => $orders['city'],
            "zip_code" => $orders['zip_code'],
            "address" => $orders['address'],
            "phone" => $orders['phone'],
            "image" => $orders['image'],
            "quantity" => $orders['quantity'],
            "title" => $orders['title']
        );
 
        array_push($post_data["data"], $order_item);
    }
}else{
	$message = "Your purchase history is empty.";
}

$post_data['status'] = $status;
$post_data['message'] = $message;


$post_data = json_encode($post_data);
echo $post_data;

mysqli_close($conn);

?>