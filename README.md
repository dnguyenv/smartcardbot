# Smart card bot recipe

This recipe provides tutorial to simulate a simple card verification system using #tjbot kit together with IBM Watson and IBM Bluemix services

## Architecture overview

<Need some diagram here to describe the architecture>

```
Client app --TLS--> node-red <---> Cloudant
                      |
                      |
                      Raspberry Pi
```

## Building and connecting components together

### Server side

Server component is composed by a node-red application, a Cloudant service and an IBM IoT platform service

<tutorial, steps to create each service goes here. Need details about how to create node-red flow and search Index in Cloudant>

Use Duy's endpoint for now (https://smartcard.mybluemix.net/ieee/postCard)

Here is the list of card IDs imported to Cloudant database:

```
SW179
SW1715
SW173
SW1717
SW174
SW1723
SW1713
SW1721
SW1714
SW1730
SW1728
SW1734
SW1726
SW1711
SW176
SW1716
SW1720
SW1729
SW1710
SW1722
SW1727
SW1719
SW1712
SW178
SW1733
SW177
SW1732
SW1731
SW1718
SW171
```

After done with server configuration, assuming you have a node-red endpoint configured at:

`https://smartcard-test.mybluemix.net/ieee/postCard`

### Device setup

This part contains source code, instruction about how to setup the Raspberry Pi and wire parts together, as well as register to Bluemix IoT platform service

Assuming you have your device setup, registered to Bluemix IoT service.

From inside your device:

Clone the recipe source code:

```
$git clone https://github.com/dnguyenv/smartcard-device.git
$cd smartcard-device
$npm install
```
Put your device information into client/device.json

```
{
        "org": "xxxxx",
        "domain": "internetofthings.ibmcloud.com",
        "type": "RaspberryPi",
        "id": "thienanbot",
        "auth-method": "token",
        "auth-token": "xxxxxxxxxxxxxxxx"
}
```

Register a Text to speech service in Bluemix and update config.js file with the credential of the service

```
// You can change the voice of the robot to your favorite voice.
exports.voice = 'en-US_LisaVoice';
// Some of the available options are:
// en-US_AllisonVoice
// en-US_LisaVoice
// en-US_MichaelVoice (the default)

//Credentials for Watson Text to Speech service
exports.TTSPassword = 'xxxxxx' ;
exports.TTSUsername = 'xxxxxx' ;

```

Run the code

```
$npm start
```

### Desktop application side

Client side is a simple desktop Java Swing application which can accept the card information red by card reader and submit the information to server component (hosted in IBM Bluemix) through a secured connection (TLS)

Clone this code

```
git clone https://github.com/dnguyenv/smartcardbot
cd smartcardbot
```

Change file CardBot.java in the client source code to use the node-red endpoint you created at server side.

Build the code:

```
sh build.sh
```

Run the code:

```
java -cp smartcard.jar com.ieee.smartcard.CardBotDemo
```
