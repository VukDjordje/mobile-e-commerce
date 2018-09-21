<?php 

header('Access-Control-Allow-Origin: *');
header('Content-type: application/json; charset=utf-8');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');

include('../../config/db_connection.php');

$data = json_decode(file_get_contents("php://input"));

$user_id = stripcslashes($data->user_id);
$city = stripcslashes($data->city);
$zip_code = stripcslashes($data->zip_code);
$address = stripcslashes($data->address);
$phone = stripcslashes($data->phone);
$quantity = stripcslashes($data->quantity);
$quantityArray = explode(';',$quantity);
$i = 0;

global $conn;

$status = 0;
$message = "";

if(empty($user_id) || empty($city) || empty($zip_code) || empty($address) || empty($phone)){
	$message = "All fields are required!";
}else{
	$sql = "select * from cart where user_id = ".$user_id." and ordered = 0";
	$results = $conn->query($sql);
		
	if(mysqli_num_rows($results) > 0){
	    while ($cart = mysqli_fetch_assoc($results)){
			$cart_id = $cart["cart_id"];
        	$sql = "insert into orders (cart_id, city, zip_code, address, phone, quantity) values(".$cart_id.", '".$city."', ".$zip_code.", '".$address."', '".$phone."', ".$quantityArray[$i].")";
			$conn->query($sql);
			
			$sql = "SELECT * FROM cart c join product p on c.product_id = p.product_id WHERE cart_id =".$cart_id." and user_id =".$user_id;
			$result = $conn->query($sql);
			$product = mysqli_fetch_assoc($result);
			$new_quantity = $product['quantity'] - $quantityArray[$i];
			$product_id = $product['product_id'];
			$sql = "UPDATE product SET quantity = ".$new_quantity." WHERE product_id = ".$product_id;	
			$conn->query($sql);	
			
			$i++;	
    	}

    	$sql = "UPDATE cart SET ordered = 1 WHERE user_id =".$user_id;
		$conn->query($sql);
			
		$message = "You have successfully ordered products, we will soon call you to confirm the order.";
		$status = 1;
	}else{
		$message = "Your basket is empty!";
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