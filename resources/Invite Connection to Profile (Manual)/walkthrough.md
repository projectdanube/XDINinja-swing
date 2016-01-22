# ALICE INVITES CONNECTION
	
## XDI M1 =alice AGENT --> =alice ENDPOINT
	
	*!:cid-1:aa[$msg]*!:uuid:m-1/$is()/(=alice)
	*!:cid-1:aa[$msg]*!:uuid:m-1/$do/(=alice/*!:cid-1:aa)$do
	(*!:cid-1:aa[$msg]*!:uuid:m-1$do/$send)....
	(*!:cid-1:aa[$msg]*!:uuid:m-1$do/$send).... see below XDI M2
	(*!:cid-1:aa[$msg]*!:uuid:m-1$do/$send)....
	(*!:cid-1:aa[$msg]*!:uuid:m-1$do/$send)....
	
## XDI M2 =alice ENDPOINT --> =bob ENDPOINT
	
	"connection invitation from =alice to =bob"
	
	=alice[$msg]*!:uuid:m-2/$is()/(=bob)
	=alice[$msg]*!:uuid:m-2/$do/(=bob/$send)$do
	(=alice[$msg]*!:uuid:m-2$do/$send)....
	(=alice[$msg]*!:uuid:m-2$do/$send).... see below XDI M3
	(=alice[$msg]*!:uuid:m-2$do/$send)....
	(=alice[$msg]*!:uuid:m-2$do/$send)....
	
## AT THIS POINT IN =bob's GRAPH:
	
	[$msg]/$has/=alice[$msg]*!:uuid:m-2
	.... see above XDI M2
	
# BOB APPROVES CONNECTION
	
## XDI M4 =bob AGENT --> =bob ENDPOINT
	
	"approval"
	
	note: M2 is re-sent by =bob AGENT to =bob ENDPOINT
	note: the LC of the outer message M4 "overrides" the LC of the nested messages M2
	
	*!:cid-1:ba[$msg]*!:uuid:m-4/$is()/(=bob)
	*!:cid-1:ba[$msg]*!:uuid:m-4/$do/(=bob/*!:cid-1:ba)$do
	(*!:cid-1:ba[$msg]*!:uuid:m-4$do/$send)....
	(*!:cid-1:ba[$msg]*!:uuid:m-4$do/$send).... see above XDI M2
	(*!:cid-1:ba[$msg]*!:uuid:m-4$do/$send)....
	(*!:cid-1:ba[$msg]*!:uuid:m-4$do/$send)....
	
	note: instead of re-sending entire message M2, we could also just reference it:
	
	*!:cid-1:ba[$msg]*!:uuid:m-4$do/$send/=alice[$msg]*!:uuid:m-2
	
## XDI M3 =bob ENDPOINT --> =alice ENDPOINT
	
	"connection request from =bob to =alice"
	
	=bob[$msg]*!:uuid:m-3/$is()/(=alice)
	=bob[$msg]*!:uuid:m-3/$do/(=alice/$connect)$do
	=bob[$msg]*!:uuid:m-3$do/$connect/$get{$do}
	=bob[$msg]*!:uuid:m-3$do/$connect/$push{$do}
	=bob[$msg]*!:uuid:m-3$connect{$get}/$is/=alice$card
	=bob[$msg]*!:uuid:m-3$connect{$push}/$is/=alice$card
	
# ALICE APPROVES CONNECTION
	
## XDI M5 =alice AGENT --> =alice ENDPOINT
	
	"approval"
	
	note: M3 is re-sent by =alice AGENT to =alice ENDPOINT
	note: the LC of the outer message M5 "overrides" the LC of the nested messages M3
	
	*!:cid-1:aa[$msg]*!:uuid:m-5/$is()/(=alice)
	*!:cid-1:aa[$msg]*!:uuid:m-5/$do/(=alice/*!:cid-1:aa)$do
	(*!:cid-1:aa[$msg]*!:uuid:m-5$do/$send)....
	(*!:cid-1:aa[$msg]*!:uuid:m-5$do/$send).... see above XDI M3
	(*!:cid-1:aa[$msg]*!:uuid:m-5$do/$send)....
	(*!:cid-1:aa[$msg]*!:uuid:m-5$do/$send)....
	
	note: instead of re-sending entire message M3, we could also just reference it:
	
	*!:cid-1:aa[$msg]*!:uuid:m-5$do/$send/=bob[$msg]*!:uuid:m-3
	
# Link Contract Templates
	
## STANDARD LINK CONTRACT TEMPLATE $msg$digest{$do}
	
	$msg$digest{$do}/$all/
	($msg$digest{$do}$if/$true){{$msg}}<$digest>/{&}/{<$digest>}
	
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
	
## LINK CONTRACT INSTANCE LC-1 created by M3
	
	(=alice/=bob)$get{$do}/$is#/$get{$do}
	(=alice/=bob)$get{$do}/$get/=alice$card
	(=alice/=bob)($get{$do}$if$and/$true){$from}/$is/=bob
	(=alice/=bob)($get{$do}$if$and/$true){$msg}<$sig><$valid>/&/true
	
## LINK CONTRACT INSTANCE LC-2 created by M3
	
	(=alice/=bob)$push{$do}/$is#/$push{$do}
	(=alice/=bob)$push{$do}/$push/=alice$card
	(=alice/=bob)$push{$do}/$is()/(=bob)
	(=alice/=bob)($push{$do}$if$and/$true){$from}/$is/=alice
	(=alice/=bob)($push{$do}$if$and/$true){$msg}<$sig><$valid>/&/true
	