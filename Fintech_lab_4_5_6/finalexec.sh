
#!/bin/bash

BASE="http://localhost:8080/api"
H="Content-Type: application/json"

echo "========== CUSTOMER DETAIL =========="

D1=$(curl -s -X POST $BASE/detail -H "$H" -d '{
  "gender":"M",
  "type":"REG",
  "dob":"1990-01-01",
  "lang":"EN",
  "status":"ACTIVE",
  "country":"US"
}')
D2=$(curl -s -X POST $BASE/detail -H "$H" -d '{
  "gender":"F",
  "type":"VIP",
  "dob":"1992-02-02",
  "lang":"FR",
  "status":"ACTIVE",
  "country":"CA"
}')

ID1=$(echo $D1 | jq -r '.id')
ID2=$(echo $D2 | jq -r '.id')

curl -s -X PUT $BASE/detail/$ID1 -H "$H" -d '{
  "id":'"$ID1"',
  "gender":"M",
  "type":"REG",
  "dob":"1990-01-01",
  "lang":"EN",
  "status":"INACTIVE",
  "country":"US"
}'

curl -s -X DELETE $BASE/detail/$ID2

echo "========== CUSTOMER NAME =========="

N1=$(curl -s -X POST $BASE/name -H "$H" -d '{
  "type":"PRIMARY",
  "value":"John Doe",
  "effectiveDate":"2024-01-01",
  "detailId":'"$ID1"'
}')
N2=$(curl -s -X POST $BASE/name -H "$H" -d '{
  "type":"SECONDARY",
  "value":"Johnny",
  "effectiveDate":"2024-01-01",
  "detailId":'"$ID1"'
}')

NID1=$(echo $N1 | jq -r '.id')
NID2=$(echo $N2 | jq -r '.id')

curl -s -X PUT $BASE/name/$NID1 -H "$H" -d '{
  "id":'"$NID1"',
  "type":"PRIMARY",
  "value":"John Updated",
  "effectiveDate":"2024-01-01",
  "detailId":'"$ID1"'
}'

curl -s -X DELETE $BASE/name/$NID2

echo "========== CUSTOMER CLASSIFICATION =========="

C1=$(curl -s -X POST $BASE/classification -H "$H" -d '{
  "type":"VIP",
  "value":"GOLD",
  "effectiveDate":"2024-01-01",
  "detailId":'"$ID1"',
  "nameId":'"$NID1"'
}')
C2=$(curl -s -X POST $BASE/classification -H "$H" -d '{
  "type":"VIP",
  "value":"SILVER",
  "effectiveDate":"2024-01-01",
  "detailId":'"$ID1"',
  "nameId":'"$NID1"'
}')

CID1=$(echo $C1 | jq -r '.id')
CID2=$(echo $C2 | jq -r '.id')

curl -s -X PUT $BASE/classification/$CID1 -H "$H" -d '{
  "id":'"$CID1"',
  "type":"VIP",
  "value":"PLATINUM",
  "effectiveDate":"2024-01-01",
  "detailId":'"$ID1"',
  "nameId":'"$NID1"'
}'

curl -s -X DELETE $BASE/classification/$CID2

echo "========== CUSTOMER ADDRESS =========="

A1=$(curl -s -X POST $BASE/address -H "$H" -d '{
  "type":"HOME",
  "value":"123 Main Street",
  "effectiveDate":"2024-01-01",
  "classificationId":'"$CID1"',
  "detailId":'"$ID1"'
}')
A2=$(curl -s -X POST $BASE/address -H "$H" -d '{
  "type":"OFFICE",
  "value":"456 Office Ave",
  "effectiveDate":"2024-01-01",
  "classificationId":'"$CID1"',
  "detailId":'"$ID1"'
}')

AID1=$(echo $A1 | jq -r '.id')
AID2=$(echo $A2 | jq -r '.id')

curl -s -X PUT $BASE/address/$AID1 -H "$H" -d '{
  "id":'"$AID1"',
  "type":"HOME",
  "value":"Updated Address",
  "effectiveDate":"2024-01-01",
  "classificationId":'"$CID1"',
  "detailId":'"$ID1"'
}'

curl -s -X DELETE $BASE/address/$AID2

echo "========== CUSTOMER CONTACT =========="

CT1=$(curl -s -X POST $BASE/contact -H "$H" -d '{
  "type":"EMAIL",
  "value":"john@example.com",
  "effectiveDate":"2024-01-01",
  "startDate":"2024-01-01",
  "endDate":"2025-01-01",
  "detailId":'"$ID1"'
}')
CT2=$(curl -s -X POST $BASE/contact -H "$H" -d '{
  "type":"PHONE",
  "value":"9999999999",
  "effectiveDate":"2024-01-01",
  "startDate":"2024-01-01",
  "endDate":"2025-01-01",
  "detailId":'"$ID1"'
}')

CTID1=$(echo $CT1 | jq -r '.id')
CTID2=$(echo $CT2 | jq -r '.id')

curl -s -X PUT $BASE/contact/$CTID1 -H "$H" -d '{
  "id":'"$CTID1"',
  "type":"EMAIL",
  "value":"updated@example.com",
  "effectiveDate":"2024-01-01",
  "startDate":"2024-01-01",
  "endDate":"2025-01-01",
  "detailId":'"$ID1"'
}'

curl -s -X DELETE $BASE/contact/$CTID2

echo "========== CUSTOMER IDENTIFICATION =========="

I1=$(curl -s -X POST $BASE/identification -H "$H" -d '{
  "type":"PASSPORT",
  "item":"A1234567",
  "effectiveDate":"2024-01-01",
  "detailId":'"$ID1"'
}')

IID1=$(echo $I1 | jq -r '.id')

curl -s -X PUT $BASE/identification/$IID1 -H "$H" -d '{
  "id":'"$IID1"',
  "type":"PASSPORT",
  "item":"UPDATED123",
  "effectiveDate":"2024-01-01",
  "detailId":'"$ID1"'
}'

echo "========== CUSTOMER PROOF OF ID =========="

P1=$(curl -s -X POST $BASE/proof -H "$H" -d '{
  "type":"AADHAAR",
  "value":"999988887777",
  "effectiveDate":"2024-01-01",
  "startDate":"2024-01-01",
  "endDate":"2030-01-01",
  "detailId":'"$ID1"'
}')
P2=$(curl -s -X POST $BASE/proof -H "$H" -d '{
  "type":"PAN",
  "value":"ABCDE1234F",
  "effectiveDate":"2024-01-01",
  "startDate":"2024-01-01",
  "endDate":"2030-01-01",
  "detailId":'"$ID1"'
}')

PID1=$(echo $P1 | jq -r '.id')
PID2=$(echo $P2 | jq -r '.id')

curl -s -X PUT $BASE/proof/$PID1 -H "$H" -d '{
  "id":'"$PID1"',
  "type":"AADHAAR",
  "value":"UPDATED9999",
  "effectiveDate":"2024-01-01",
  "startDate":"2024-01-01",
  "endDate":"2030-01-01",
  "detailId":'"$ID1"'
}'

curl -s -X DELETE $BASE/proof/$PID2

echo "========== DONE =========="

