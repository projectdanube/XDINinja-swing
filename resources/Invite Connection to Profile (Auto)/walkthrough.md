# ALICE INVITES CONNECTION
	
## XDI M1 =alice AGENT --> =alice ENDPOINT
	
	*!:cid-1:aa[$msg]*!:uuid:m-1/$to/(=alice)
	*!:cid-1:aa[$msg]*!:uuid:m-1/$contract/(=alice/*!:cid-1:aa)$contract
	(*!:cid-1:aa[$msg]*!:uuid:m-1$do/$send)....
	(*!:cid-1:aa[$msg]*!:uuid:m-1$do/$send).... see below XDI M2
	(*!:cid-1:aa[$msg]*!:uuid:m-1$do/$send)....
	(*!:cid-1:aa[$msg]*!:uuid:m-1$do/$send)....
	(*!:cid-1:aa[$msg]*!:uuid:m-1$do/$send)....
	(*!:cid-1:aa[$msg]*!:uuid:m-1$do/$send).... see below XDI M3
	(*!:cid-1:aa[$msg]*!:uuid:m-1$do/$send)....
	(*!:cid-1:aa[$msg]*!:uuid:m-1$do/$send)....
	
## XDI M2 =alice ENDPOINT --> =alice ENDPOINT
	
	"create digest link contract MD-LC-1 for M5"
	
## XDI M3 =alice ENDPOINT --> =bob ENDPOINT
	
	"connection invitation from =alice to =bob"
	
	=alice[$msg]*!:uuid:m-3/$to/(=bob)
	=alice[$msg]*!:uuid:m-3/$contract/(=bob/$send)$contract
	(=alice[$msg]*!:uuid:m-3$do/$send)....
	(=alice[$msg]*!:uuid:m-3$do/$send).... see below XDI M4
	(=alice[$msg]*!:uuid:m-3$do/$send)....
	(=alice[$msg]*!:uuid:m-3$do/$send)....
	
## AT THIS POINT IN =bob's GRAPH:
	
	[$msg]/$has/=alice[$msg]*!:uuid:m-3
	.... see above XDI M3
	
# BOB APPROVES CONNECTION
	
## XDI M5 =bob AGENT --> =bob ENDPOINT
	
	"approval"
	
	note: M3 is re-sent by =bob AGENT to =bob ENDPOINT
	note: the LC of the outer message M5 "overrides" the LC of the nested messages M3
	
	*!:cid-1:ba[$msg]*!:uuid:m-5/$to/(=bob)
	*!:cid-1:ba[$msg]*!:uuid:m-5/$contract/(=bob/*!:cid-1:ba)$contract
	(*!:cid-1:ba[$msg]*!:uuid:m-5$do/$send)....
	(*!:cid-1:ba[$msg]*!:uuid:m-5$do/$send).... see above XDI M3
	(*!:cid-1:ba[$msg]*!:uuid:m-5$do/$send)....
	(*!:cid-1:ba[$msg]*!:uuid:m-5$do/$send)....
	
	note: instead of re-sending entire message M3, we could also just reference it:
	
	*!:cid-1:ba[$msg]*!:uuid:m-5$do/$send/=alice[$msg]*!:uuid:m-3
	
## XDI M4 =bob ENDPOINT --> =alice ENDPOINT
	
	"connection request from =bob to =alice"
	
	=bob[$msg]*!:uuid:m-4/$to/(=alice)
	=bob[$msg]*!:uuid:m-4/$do/$msg$digest[$contract]*!:uuid:md-lc-1
	=bob[$msg]*!:uuid:m-4$do/$connect/$get{$contract}
	=bob[$msg]*!:uuid:m-4$do/$connect/$push{$contract}
	=bob[$msg]*!:uuid:m-4$connect{$get}/$is/=alice$card
	=bob[$msg]*!:uuid:m-4$connect{$push}/$is/=alice$card
	
# Link Contract Templates
	
## STANDARD LINK CONTRACT TEMPLATE $msg$digest{$contract}
	
	$msg$digest{$contract}/$all/
	($msg$digest{$contract}$do$if/$true){{$msg}}<$digest>/{&}/{<$digest>}
	
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
	
## LINK CONTRACT INSTANCE MD-LC-1 created by M2
	
	(=alice/=bob)$msg$digest[$contract]*!:uuid:md-lc-1/$is#/$msg$digest{$contract}
	(=alice/=bob)$msg$digest[$contract]*!:uuid:md-lc-1/$all/
	(=alice/=bob)($msg$digest{$contract}$do$if/$true){$msg}<$digest>/&/"... message digest ..."
	
## LINK CONTRACT INSTANCE LC-1 created by M4
	
	(=alice/=bob)$get{$contract}/$is#/$get{$contract}
	(=alice/=bob)$get{$contract}/$get/=alice$card
	(=alice/=bob)($get{$contract}$do$if$and/$true){$from}/$is/=bob
	(=alice/=bob)($get{$contract}$do$if$and/$true){$msg}<$sig><$valid>/&/true
	
## LINK CONTRACT INSTANCE LC-2 created by M4
	
	(=alice/=bob)$push{$contract}/$is#/$push{$contract}
	(=alice/=bob)$push{$contract}/$push/=alice$card
	(=alice/=bob)$push{$contract}/$to/(=bob)
	(=alice/=bob)($push{$contract}$do$if$and/$true){$from}/$is/=alice
	(=alice/=bob)($push{$contract}$do$if$and/$true){$msg}<$sig><$valid>/&/true
	