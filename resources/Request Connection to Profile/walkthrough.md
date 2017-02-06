# ALICE REQUESTS CONNECTION
	
## XDI M1 =alice AGENT --> =alice ENDPOINT
	
	*!:cid-1:aa[$msg]*!:uuid:m-1/$to/(=alice)
	*!:cid-1:aa[$msg]*!:uuid:m-1/$contract/(=alice/*!:cid-1:aa)$contract
	(*!:cid-1:aa[$msg]*!:uuid:m-1$do/$send)....
	(*!:cid-1:aa[$msg]*!:uuid:m-1$do/$send).... see below XDI M2
	(*!:cid-1:aa[$msg]*!:uuid:m-1$do/$send)....
	(*!:cid-1:aa[$msg]*!:uuid:m-1$do/$send)....
	
## XDI M2 =alice ENDPOINT --> =bob ENDPOINT
	
	"connection request from =alice to =bob"
	
	=alice[$msg]*!:uuid:m-2/$to/(=bob)
	=alice[$msg]*!:uuid:m-2/$contract/(=bob/$connect)$contract
	=alice[$msg]*!:uuid:m-2$do/$connect/$get{$contract}
	=alice[$msg]*!:uuid:m-2$do/$connect/$push{$contract}
	=alice[$msg]*!:uuid:m-2$connect{$get}/$is/=bob$card
	=alice[$msg]*!:uuid:m-2$connect{$push}/$is/=bob$card
	
## AT THIS POINT IN =bob's GRAPH:
	
	[$msg]/$has/=alice[$msg]*!:uuid:m-2
	.... see above XDI M2
	
# BOB APPROVES CONNECTION
	
## XDI M3 =bob AGENT --> =bob ENDPOINT
	
	"approval"
	
	note: M2 is re-sent by =bob AGENT to =bob ENDPOINT
	note: the LC of the outer message M3 "overrides" the LCs of the nested message M2
	
	*!:cid-1:ba[$msg]*!:uuid:m-3/$to/(=bob)
	*!:cid-1:ba[$msg]*!:uuid:m-3/$contract/(=bob/*!:cid-1:ba)$contract
	(*!:cid-1:ba[$msg]*!:uuid:m-3$do/$send)....
	(*!:cid-1:ba[$msg]*!:uuid:m-3$do/$send).... see above XDI M2
	(*!:cid-1:ba[$msg]*!:uuid:m-3$do/$send)....
	(*!:cid-1:ba[$msg]*!:uuid:m-3$do/$send)....
	
	note: instead of re-sending entire message M2, we could also just reference it:
	
	*!:cid-1:ba[$msg]*!:uuid:m-3$do/$send/=alice[$msg]*!:uuid:m-2
	
# Link Contract Templates
	
## STANDARD LINK CONTRACT TEMPLATE $get{$contract}
	
	$get{$contract}/$get/{$get}
	($get{$contract}$do$if$and/$true){{$from}}/$is/{$from}
	($get{$contract}$do$if$and/$true){{$msg}}<$sig><$valid>/&/true
	
## STANDARD LINK CONTRACT TEMPLATE $push{$contract}
	
	$push{$contract}/$push/{$push}
	$push{$contract}/$to/{($from)}
	($push{$contract}$do$if$and/$true){{$from}}/$is/{$to}
	($push{$contract}$do$if$and/$true){{$msg}}<$sig><$valid>/&/true
	
# Link Contracts
	
## LINK CONTRACT INSTANCE LC-1 created by M2
	
	(=bob/=alice)$get$do/$is#/$get{$contract}
	(=bob/=alice)$get$do/$get/=bob$card
	(=bob/=alice)($get$contract$do$if$and/$true){$from}/$is/=alice
	(=bob/=alice)($get$contract$do$if$and/$true){$msg}<$sig><$valid>/&/true
	
## LINK CONTRACT INSTANCE LC-2 created by M2
	
	(=bob/=alice)$push$do/$is#/$push{$contract}
	(=bob/=alice)$push$do/$push/=bob$card
	(=bob/=alice)$push$do/$to/(=alice)
	(=bob/=alice)($push$contract$do$if$and/$true){$from}/$is/=bob
	(=bob/=alice)($push$contract$do$if$and/$true){$msg}<$sig><$valid>/&/true
	