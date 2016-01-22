# ALICE REQUESTS/INVITES CONNECTION
	
## XDI M1 =alice AGENT --> =alice ENDPOINT
	
	*!:cid-1:aa[$msg]*!:uuid:m-1/$is()/(=alice)
	*!:cid-1:aa[$msg]*!:uuid:m-1/$do/(=alice/*!:cid-1:aa)$do
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
	
	"create digest link contract LC-1 for M5"
	
## XDI M3 =alice ENDPOINT --> =bob ENDPOINT
	
	"connection request from =alice to =bob"
	
	=alice[$msg]*!:uuid:m-3/$is()/(=bob)
	=alice[$msg]*!:uuid:m-3/$do/(=bob/$connect)$do
	=alice[$msg]*!:uuid:m-3$do/$connect/$set{$do}
	=alice[$msg]*!:uuid:m-3$connect{$set}/$is/=bob#chat[$msg]
	
## XDI M4 =alice ENDPOINT --> =bob ENDPOINT
	
	"connection invitation from =alice to =bob"
	
	=alice[$msg]*!:uuid:m-4/$is()/(=bob)
	=alice[$msg]*!:uuid:m-4/$do/(=bob/$send)$do
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
	
	*!:cid-1:ba[$msg]*!:uuid:m-6/$is()/(=bob)
	*!:cid-1:ba[$msg]*!:uuid:m-6/$do/(=bob/*!:cid-1:ba)$do
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
	
	=bob[$msg]*!:uuid:m-5/$is()/(=alice)
	=bob[$msg]*!:uuid:m-5/$do/$msg$digest[$do]*!:uuid:lc-1
	=bob[$msg]*!:uuid:m-5$do/$connect/$set{$do}
	=bob[$msg]*!:uuid:m-5$connect{$set}/$is/=alice#chat[$msg]
	
# Link Contract Templates
	
## STANDARD LINK CONTRACT TEMPLATE $msg$digest{$do}
	
	$msg$digest{$do}/$all/
	($msg$digest{$do}$if/$true){{$msg}}<$digest>/{&}/{<$digest>}
	
## STANDARD LINK CONTRACT TEMPLATE $set{$do}
	
	$set{$do}/$set/{$set}
	($set{$do}$if$and/$true){{$from}}/$is/{$from}
	($set{$do}$if$and/$true){{$msg}}<$sig><$valid>/&/true
	
# Link Contracts
	
## LINK CONTRACT INSTANCE LC-1 created by M2
	
	(=alice/=bob)$msg$digest[$do]*!:uuid:lc-1/$is#/$msg$digest{$do}
	(=alice/=bob)$msg$digest[$do]*!:uuid:lc-1/$all/
	(=alice/=bob)($msg$digest{$do}$if/$true){$msg}<$digest>/&/"... message digest ..."
	
## LINK CONTRACT INSTANCE LC-2 created by M3
	
	(=bob/=alice)$set$do/$is#/$set{$do}
	(=bob/=alice)$set$do/$set/=bob#chat[$msg]
	(=bob/=alice)($set$do$if$and/$true){$from}/$is/=alice
	(=bob/=alice)($set$do$if$and/$true){$msg}<$sig><$valid>/&/true
	
## LINK CONTRACT INSTANCE LC-3 created by M5
	
	(=alice/=bob)$set$do/$is#/$set{$do}
	(=alice/=bob)$set$do/$set/=alice#chat[$msg]
	(=alice/=bob)($set$do$if$and/$true){$from}/$is/=bob
	(=alice/=bob)($set$do$if$and/$true){$msg}<$sig><$valid>/&/true
	
