<?php

define('HOST','localhost');
define('DB','id6975752_emobile');
define('USER','id6975752_emobile');
define('PASS','emobile123');

$conn = new mysqli(HOST, USER, PASS, DB);

if ($conn->connect_error) {
    die('Connect Error: ' . $conn->connect_error);
}

$conn->set_charset("utf8");

?>