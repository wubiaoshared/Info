<?php 
	header("content-type:text/html;charset:utf-8");
	set_time_limit(0);
	date_default_timezone_set("Asia/Shanghai");

	$domains = array("crusher.com","jawcrusher.co.in","mobilecrusher.pw","conecrusher.co.in","lxpdeyumng.com");

	$start = date("Y-m-d")." 03:55:00";
	$end = date("Y-m-d")." 04:30:00";


	$json = getJson($domains);
	$domain_status = getDomainStatus($json,$domains);
	print_r(checkAvailable($domain_status));

	//检查可以注册的域名
	function checkAvailable($domains_status) {
		$arrayKeys = array_keys($domains_status);
		$i=0;
		foreach ($arrayKeys as $domain) {
			if($domains_status[$domain]["status"]=="available") {
				$ava_domains[$i] = $domain;
				$i++;
			}
		}
		return $ava_domains;
	}

	//获取域名状态json数据
	function getJson($domains) {
		return sendData(getDomainCheckUrl(getDomainConsist($domains)));
	}
	/**
	 * 获取需要注册域名的status
	 * @param  string $json 所有域名json状态信息
	 * @param  array $domains        需要注册的域名
	 * @return array                需要注册域名的状态信息
	 */
	function getDomainStatus($json,$domains) {
		$domains_status = json_decode($json,true);
		$keys = array_keys($domains_status);
		foreach($keys as $domain) {
			if(in_array($domain, $domains)) {
				$r_domains_status[$domain] = $domains_status[$domain];
			}
		}
		return $r_domains_status;
	}
	//返回域名的构成数组
	function getDomainConsist($domains) {
		$size = count($domains);
		if($size>0) {
			for($i=0;$i<$size;$i++) {
				$domain_name = current(explode(".", $domains[$i]));
				$tlds = str_ireplace($domain_name.".", "", $domains[$i]);
				$fdomains[$i]["domain_name"] = $domain_name;
				$fdomains[$i]["tlds"] = $tlds;
			}
			return $fdomains;
		}
	}
	
	//获取检查域名url,需要传入域名的构成数组
	function getDomainCheckUrl($fdomains) {
		$checkUrl = "https://test.httpapi.com/api/domains/available.json?auth-userid=646061&api-key=sP6NPKSyagaoitKcihSlMMdAEmot3zFq";
		if(count($fdomains)>0) {
			$domain_name_array = i_array_column($fdomains,"domain_name");
			$tlds_array = i_array_column($fdomains,"tlds");
			$domain_name_url = join("&domain-name=",$domain_name_array);
			$tlds_url = join("&tlds=",$tlds_array);
			return $checkUrl."&domain-name=".$domain_name_url."&tlds=".$tlds_url;
		}
	}
	//获取域名注册url
	function getDomainRegUrl($domain) {
		return "https://test.httpapi.com/api/domains/register.xml?auth-userid=646061&api-key=sP6NPKSyagaoitKcihSlMMdAEmot3zFq&domain-name={$domain}&years=1&ns=ns1.domain.com&ns=ns2.domain.com&customer-id=14663552&reg-contact-id=53312520&admin-contact-id=53312520&tech-contact-id=53312520&billing-contact-id=53312520&invoice-option=PayInvoice";
	}
	//发送数据
	function sendData($url) {
		$ch = curl_init();
		curl_setopt($ch, CURLOPT_URL, $url);
		curl_setopt($ch, CURLOPT_HEADER, 0);
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
		curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
		$rs = curl_exec($ch);
		curl_close($ch);
		return $rs;
	}
	//返回当前时间状态
	function getTimeStatus($start,$end) {
		$start_time = strtotime($start);
		$end_time = strtotime($end);
		$current_time = time();
		if($current_time>=$start_time&&$current_time<=$end_time) {
			return true;
		}else {
			return false;
		}
	}
	//根据不同时间段，设置随机时间 4.到4.05之间是快速时间
	function getPostTimeLine($start,$end) {
		$start_time = strtotime($start);
		$end_time = strtotime($end);
		$current_time = time();
		$hot_time = strtotime(date("Y-m-d")." 04:00:00");
		if($hot_time > $end_time) {
			exit("结束时间设置太早");
		}
		if($current_time<$start_time) {
			$w_time = getRandomFloat(55,67);
		}else if($current_time>$start_time && $current_time<$hot_time) {
			$w_time = getRandomFloat(2.9,3.3);
		}else if($current_time>=$hot_time && $current_time<=$hot_time+300) {
			$w_time = getRandomFloat(0.8,1.5);
		}else if($current_time > $hot_time && $current_time <= $end_time) {
			$w_time = getRandomFloat(1.2,2.5);
		}else if($current_time > $end_time) {
			$w_time = getRandomFloat(3500,3610);
		}
		return $w_time;
	}
	function getRandomFloat($min,$max) {
		return round($min + mt_rand()/mt_getrandmax() * ($max - $min),2);
	}
	//按字段返回新数组 兼容低版本php
	function i_array_column($input, $columnKey, $indexKey=null){
	    if(!function_exists('array_column')){
	        $columnKeyIsNumber = (is_numeric($columnKey))?true:false;
	        $indexKeyIsNull = (is_null($indexKey))?true :false;
	        $indexKeyIsNumber = (is_numeric($indexKey))?true:false;
	        $result = array();
	        foreach((array)$input as $key=>$row){
	            if($columnKeyIsNumber){
	                $tmp = array_slice($row, $columnKey, 1);
	                $tmp = (is_array($tmp) && !empty($tmp))?current($tmp):null;
	            }else{
	                $tmp = isset($row[$columnKey])?$row[$columnKey]:null;
	            }
	            if(!$indexKeyIsNull){
	                if($indexKeyIsNumber){
	                  $key = array_slice($row, $indexKey, 1);
	                  $key = (is_array($key) && !empty($key))?current($key):null;
	                  $key = is_null($key)?0:$key;
	                }else{
	                  $key = isset($row[$indexKey])?$row[$indexKey]:0;
	                }
	            }
	            $result[$key] = $tmp;
	        }
	        return $result;
	    }else{
	        return array_column($input, $columnKey, $indexKey);
	    }
	}
 ?>