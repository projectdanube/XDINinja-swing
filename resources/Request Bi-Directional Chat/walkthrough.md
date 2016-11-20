# ALICE REQUESTS/INVITES CONNECTION
	
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
	(*!:cid-1:aa[$msg]*!:uuid:m-1$do/$send)....
	(*!:cid-1:aa[$msg]*!:uuid:m-1$do/$send).... see below XDI M4
	(*!:cid-1:aa[$msg]*!:uuid:m-1$do/$send)....
	(*!:cid-1:aa[$msg]*!:uuid:m-1$do/$send)....
	
## XDI M2 =alice ENDPOINT --> =alice ENDPOINT
	
	"create digest link contract MD-LC-1 for M5"
	
## XDI M3 =alice ENDPOINT --> =bob ENDPOINT
	
	"connection request from =alice to =bob"
	
	=alice[$msg]*!:uuid:m-3/$to/(=bob)
	=alice[$msg]*!:uuid:m-3/$contract/(=bob/$connect)$contract
	=alice[$msg]*!:uuid:m-3$do/$connect/$set{$contract}
	=alice[$msg]*!:uuid:m-3$connect{$set}/$is/=bob#chat[$msg]
	
## XDI M4 =alice ENDPOINT --> =bob ENDPOINT
	
	"connection invitation from =alice to =bob"
	
	=alice[$msg]*!:uuid:m-4/$to/(=bob)
	=alice[$msg]*!:uuid:m-4/$contract/(=bob/$send)$contract
	(=alice[$msg]*!:uuid:m-4$do/$send)....
	(=alice[$msg]*!:uuid:m-4$do/$send).... see below XDI M5
	(=alice[$msg]*!:uuid:m-4$do/$send)....
	(=alice[$msg]*!:uuid:m-4$do/$send)....
	
## AT THIS POINT IN =bob's GRAPH:
	
	[$msg]/$has/=alice[$msg]*!:uuid:m-3
	[$msg]/$has/=alice[$msg]*!:uuid:m-4
	.... see above XDI M3
	.... see above XDI M4
	
# BOB APPROVES CONNECTION
	
## XDI M6 =bob AGENT --> =bob ENDPOINT
	
	"approval"
	
	note: M3 and M4 are re-sent by =bob AGENT to =bob ENDPOINT
	note: the LC of the outer message M6 "overrides" the LCs of the nested messages M3 and M4
	
	*!:cid-1:ba[$msg]*!:uuid:m-6/$to/(=bob)
	*!:cid-1:ba[$msg]*!:uuid:m-6/$contract/(=bob/*!:cid-1:ba)$contract
	(*!:cid-1:ba[$msg]*!:uuid:m-6$do/$send)....
	(*!:cid-1:ba[$msg]*!:uuid:m-6$do/$send).... see above XDI M3
	(*!:cid-1:ba[$msg]*!:uuid:m-6$do/$send)....
	(*!:cid-1:ba[$msg]*!:uuid:m-6$do/$send)....
	(*!:cid-1:ba[$msg]*!:uuid:m-6$do/$send)....
	(*!:cid-1:ba[$msg]*!:uuid:m-6$do/$send).... see above XDI M4
	(*!:cid-1:ba[$msg]*!:uuid:m-6$do/$send)....
	(*!:cid-1:ba[$msg]*!:uuid:m-6$do/$send)....
	
	note: instead of re-sending entire messages M3 and M4, we could also just reference them:
	
	*!:cid-1:ba[$msg]*!:uuid:m-6$do/$send/=alice[$msg]*!:uuid:m-3
	*!:cid-1:ba[$msg]*!:uuid:m-6$do/$send/=alice[$msg]*!:uuid:m-4
	
## XDI M5 =bob ENDPOINT --> =alice ENDPOINT
	
	"connection request from =bob to =alice"
	
	=bob[$msg]*!:uuid:m-5/$to/(=alice)
	=bob[$msg]*!:uuid:m-5/$do/$msg$digest[$contract]*!:uuid:md-lc-1
	=bob[$msg]*!:uuid:m-5$do/$connect/$set{$contract}
	=bob[$msg]*!:uuid:m-5$connect{$set}/$is/=alice#chat[$msg]
	
# Link Contract Templates
	
## STANDARD LINK CONTRACT TEMPLATE $msg$digest{$contract}
	
	$msg$digest{$contract}/$all/
	($msg$digest{$contract}$if/$true){{$msg}}<$digest>/{&}/{<$digest>}
	
## STANDARD LINK CONTRACT TEMPLATE $set{$contract}
	
	$set{$contract}/$set/{$set}
	($set{$contract}$if$and/$true){{$from}}/$is/{$from}
	($set{$contract}$if$and/$true){{$msg}}<$sig><$valid>/&/true
	
# Link Contracts
	
## LINK CONTRACT INSTANCE MD-LC-1 created by M2
	
	(=alice/=bob)$msg$digest[$contract]*!:uuid:md-lc-1/$is#/$msg$digest{$contract}
	(=alice/=bob)$msg$digest[$contract]*!:uuid:md-lc-1/$all/
	(=alice/=bob)($msg$digest[$contract]*!:uuid:md-lc-1$if/$true){$msg}<$digest>/&/"... message digest ..."
	
## LINK CONTRACT INSTANCE LC-1 created by M3
	
	(=bob/=alice)$set$do/$is#/$set{$contract}
	(=bob/=alice)$set$do/$set/=bob#chat[$msg]
	(=bob/=alice)($set$contract$if$and/$true){$from}/$is/=alice
	(=bob/=alice)($set$contract$if$and/$true){$msg}<$sig><$valid>/&/true
	
## LINK CONTRACT INSTANCE LC-2 created by M5
	
	(=alice/=bob)$set$do/$is#/$set{$contract}
	(=alice/=bob)$set$do/$set/=alice#chat[$msg]
	(=alice/=bob)($set$contract$if$and/$true){$from}/$is/=bob
	(=alice/=bob)($set$contract$if$and/$true){$msg}<$sig><$valid>/&/true
	
