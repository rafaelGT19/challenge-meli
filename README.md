# challenge-meli

Given a customer coupon with a list of favorites item ids and a limit value to spend, returns the subset of items whose maximize the amount spent.

Api endpoint: POST http://pruebasmeli-env.eba-xsnekwiv.us-east-2.elasticbeanstalk.com/coupon

Request body example,
```json
{
    "item_ids": [
        "MCO465334695",
        "MCO540428115",
        "MCO549243369",
        "MCO562013916",
        "MCO560612869",
        "MCO566778453"
    ],
    "amount": 500000
}
```
Response body example,
```json
{
    "total": 385730.0,
    "item_ids": [
        "MCO560612869",
        "MCO566778453",
        "MCO562013916"
    ]
}
```
Example,
```shell
curl --header "Content-Type: application/json" --request POST --data '{"item_ids": ["MCO465334695","MCO540428115"],"amount": 500000}' http://pruebasmeli-env.eba-xsnekwiv.us-east-2.elasticbeanstalk.com/coupon
```

### http response codes
| Http Code                       | Description                   | 
| --------------------------------- |:-----------------------:| 
| 200      | Coupon was processed successfully  | 
| 404 | Insufficient amount to buy an item  |
| 503 | Error occurred processing request  |
