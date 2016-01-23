# ALICE REQUESTS CONNECTION
	
## XDI M1 =alice AGENT --> =alice ENDPOINT
	
	*!:cid-1:aa[$msg]*!:uuid:m-1/$is()/(=alice)
	*!:cid-1:aa[$msg]*!:uuid:m-1/$do/(=alice/*!:cid-1:aa)$do
	(*!:cid-1:aa[$msg]*!:uuid:m-1$do/$send)....
	(*!:cid-1:aa[$msg]*!:uuid:m-1$do/$send).... see below XDI M2
	(*!:cid-1:aa[$msg]*!:uuid:m-1$do/$send)....
	(*!:cid-1:aa[$msg]*!:uuid:m-1$do/$send)....
	
## XDI M2 =alice ENDPOINT --> =bob ENDPOINT
	
	"connection request from =alice to =bob"
	
	=alice[$msg]*!:uuid:m-2/$is()/(=bob)
	=alice[$msg]*!:uuid:m-2/$do/(=bob/$connect)$do
	=alice[$msg]*!:uuid:m-2$do/$connect/$get{$do}
	=alice[$msg]*!:uuid:m-2$do/$connect/$push{$do}
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
	
	*!:cid-1:ba[$msg]*!:uuid:m-3/$is()/(=bob)
	*!:cid-1:ba[$msg]*!:uuid:m-3/$do/(=bob/*!:cid-1:ba)$do
	(*!:cid-1:ba[$msg]*!:uuid:m-3$do/$send)....
	(*!:cid-1:ba[$msg]*!:uuid:m-3$do/$send).... see above XDI M2
	(*!:cid-1:ba[$msg]*!:uuid:m-3$do/$send)....
	(*!:cid-1:ba[$msg]*!:uuid:m-3$do/$send)....
	
	note: instead of re-sending entire message M2, we could also just reference it:
	
	*!:cid-1:ba[$msg]*!:uuid:m-3$do/$send/=alice[$msg]*!:uuid:m-2
	
# Link Contract Templates
	
## STANDARD LINK CONTRACT TEMPLATE $get{$do}
	
	$get{$do}/$get/{$get}
	($get{$do}$if$and/$true){{$from}}/$is/{$from}
	($get{$do}$if$and/$true){{$msg}}<$sig><$valid>/&/true
	
## STANDARD LINK CONTRACT TEMPLATE $push{$do}
	
	$push{$do}/$push/{$push}
	$push{$do}/$is()/{($from)}
	($push{$do}$if$and/$true){{$from}}/$is/{$to}
	($push{$do}$if$and/$true){{$msg}}<$sig><$valid>/&/true
	
# Link Contracts
	
## LINK CONTRACT INSTANCE LC-1 created by M2
	
	(=bob/=alice)$get$do/$is#/$get{$do}
	(=bob/=alice)$get$do/$get/=bob$card
	(=bob/=alice)($get$do$if$and/$true){$from}/$is/=alice
	(=bob/=alice)($get$do$if$and/$true){$msg}<$sig><$valid>/&/true
	
## LINK CONTRACT INSTANCE LC-2 created by M2
	
	(=bob/=alice)$push$do/$is#/$push{$do}
	(=bob/=alice)$push$do/$push/=bob$card
	(=bob/=alice)$push$do/$is()/(=alice)
	(=bob/=alice)($push$do$if$and/$true){$from}/$is/=bob
	(=bob/=alice)($push$do$if$and/$true){$msg}<$sig><$valid>/&/true
	