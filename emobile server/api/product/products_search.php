<<<<<<< HEAD
<?php
 
header('Access-Control-Allow-Origin: *');
header('Content-type: application/json; charset=utf-8');
header('Access-Control-Allow-Methods: GET');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');
 
include('../../config/db_connection.php');
 
if(isset($_GET['q'])){
    $q = $_GET['q'];
}else{
    http_response_code(400);
    die();
}
 
global $conn;
 
$status = 0;
$message = "";
 
$sql = "SELECT * FROM product WHERE title LIKE '%$q%' OR description LIKE '%$q%'";
$results = $conn->query($sql);
 
$post_data = array();
 
if(mysqli_num_rows($results)>0){
 
    // products array
    $post_data["data"]=array();
    $status = 1;
    $message = "Successfully found products";
 
    while ($product = mysqli_fetch_assoc($results)){
        $quantity = $product['quantity'];
        $product_item=array(
            "product_id" => $product['product_id'],
            "title" => $product['title'],
            "price" => $product['price'],
            "brand_id" => $product['brand_id'],
            "category_id" => $product['category_id'],
            "image" => $product['image'],
            "description" => $product['description'],
            "featured" => $product['featured'],
            "quantity" => $quantity
        );
        if($quantity > 0){
            array_push($post_data["data"], $product_item);
        }
    }
}else{
    $message = "We did not find any products";
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
 
if(isset($_GET['q'])){
    $q = $_GET['q'];
}else{
    http_response_code(400);
    die();
}
 
global $conn;
 
$status = 0;
$message = "";
 
$sql = "SELECT * FROM product WHERE title LIKE '%$q%' OR description LIKE '%$q%'";
$results = $conn->query($sql);
 
$post_data = array();
 
if(mysqli_num_rows($results)>0){
 
    // products array
    $post_data["data"]=array();
    $status = 1;
    $message = "Successfully found products";
 
    while ($product = mysqli_fetch_assoc($results)){
        $quantity = $product['quantity'];
        $product_item=array(
            "product_id" => $product['product_id'],
            "title" => $product['title'],
            "price" => $product['price'],
            "brand_id" => $product['brand_id'],
            "category_id" => $product['category_id'],
            "image" => $product['image'],
            "description" => $product['description'],
            "featured" => $product['featured'],
            "quantity" => $quantity
        );
        if($quantity > 0){
            array_push($post_data["data"], $product_item);
        }
    }
}else{
    $message = "We did not find any products";
}
 
$post_data['status'] = $status;
$post_data['message'] = $message;
 
 
$post_data = json_encode($post_data);
echo $post_data;
 
mysqli_close($conn);
 
>>>>>>> 709756eda9aab0678399c2cfa2229241dccc7dcd
?>