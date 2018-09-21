<<<<<<< HEAD
<?php 

header('Access-Control-Allow-Origin: *');
header('Content-type: application/json; charset=utf-8');
header('Access-Control-Allow-Methods: GET');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');

include('../../config/db_connection.php');

global $conn;

$status = 0;
$message = "";

$sql = "SELECT * FROM product WHERE featured = 1";
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
    $post_data["banner"] = array();
    $sql = "SELECT * FROM banner";
    $results = $conn->query($sql);
   
    while ($banner = mysqli_fetch_assoc($results)){
        $banner_item = array(
            "banner_id" => $banner['banner_id'],
            "image" => $banner['image']
        );
        array_push($post_data["banner"], $banner_item);
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

global $conn;

$status = 0;
$message = "";

$sql = "SELECT * FROM product WHERE featured = 1";
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
    $post_data["banner"] = array();
    $sql = "SELECT * FROM banner";
    $results = $conn->query($sql);
   
    while ($banner = mysqli_fetch_assoc($results)){
        $banner_item = array(
            "banner_id" => $banner['banner_id'],
            "image" => $banner['image']
        );
        array_push($post_data["banner"], $banner_item);
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