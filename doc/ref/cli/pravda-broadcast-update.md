<!--
THIS FILE IS GENERATED. DO NOT EDIT MANUALLY!
-->

```pravda broadcast update --wallet <file> --limit <long> --price <long> --endpoint <string> --input <file> --program <string>```

## Description
Update existing Pravda program in the blockchain.
## Options

|Option|Description|
|----|----|
|`-w`, `--wallet`|File with user wallet. You can obtain it using 'pravda gen address' command. Format: {"address": <public key>, "privateKey": <private key>}
|`-l`, `--limit`|Watt limit (300 by default).
|`-p`, `--price`|Watt price (1 by default).
|`-e`, `--endpoint`|Node endpoint (http://localhost:8080/api/public/broadcast by default).
|`-i`, `--input`|Input file.
|`-p`, `--program`|Address of the program to update
