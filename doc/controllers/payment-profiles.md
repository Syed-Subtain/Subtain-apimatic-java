# Payment Profiles

```java
PaymentProfilesController paymentProfilesController = client.getPaymentProfilesController();
```

## Class Name

`PaymentProfilesController`

## Methods

* [Create Payment Profile](../../doc/controllers/payment-profiles.md#create-payment-profile)
* [List Payment Profiles](../../doc/controllers/payment-profiles.md#list-payment-profiles)
* [Read Payment Profile](../../doc/controllers/payment-profiles.md#read-payment-profile)
* [Update Payment Profile](../../doc/controllers/payment-profiles.md#update-payment-profile)
* [Delete Unused Payment Profile](../../doc/controllers/payment-profiles.md#delete-unused-payment-profile)
* [Delete Subscriptions Payment Profile](../../doc/controllers/payment-profiles.md#delete-subscriptions-payment-profile)
* [Verify Bank Account](../../doc/controllers/payment-profiles.md#verify-bank-account)
* [Delete Subscription Group Payment Profile](../../doc/controllers/payment-profiles.md#delete-subscription-group-payment-profile)
* [Update Subscription Default Payment Profile](../../doc/controllers/payment-profiles.md#update-subscription-default-payment-profile)
* [Update Subscription Group Default Payment Profile](../../doc/controllers/payment-profiles.md#update-subscription-group-default-payment-profile)
* [Read One Time Token](../../doc/controllers/payment-profiles.md#read-one-time-token)
* [Send Request Update Payment Email](../../doc/controllers/payment-profiles.md#send-request-update-payment-email)


# Create Payment Profile

Use this endpoint to create a payment profile for a customer.

Payment Profiles house the credit card, ACH (Authorize.Net or Stripe only,) or PayPal (Braintree only,) data for a customer. The payment information is attached to the customer within Chargify, as opposed to the Subscription itself.

You must include a customer_id so that Chargify will attach it to the customer entry. If no customer_id is included the API will return a 404.

## Create a Payment Profile for ACH usage

If you would like to create a payment method that is a Bank Account applicable for ACH payments use the following:

```json
{
"payment_profile": {
  "customer_id": [Valid-Customer-ID],
  "bank_name": "Best Bank",
  "bank_routing_number": "021000089",
  "bank_account_number": "111111111111",
  "bank_account_type": "checking",
  "bank_account_holder_type": "business",
  "payment_type": "bank_account"
  }
}
```

## Taxable Subscriptions

If your subscriber pays taxes on their purchased product, and you are attempting to create or update the `payment_profile`, complete address information is required. For information on required address formatting to allow your subscriber to be taxed, please see our documentation [here](https://developers.chargify.com/docs/developer-docs/d2e9e34db740e-signups#taxes)

## Payment Profile Documentation

Full documentation on how Payment Profiles operate within Chargify can be located under the following links:

+ [Subscriber Payment Details](https://maxio-chargify.zendesk.com/hc/en-us/articles/5405212558349-Customers-Reference#customers-reference-0-0)
+ [Self Service Pages](https://maxio-chargify.zendesk.com/hc/en-us/articles/5404759627021) (Allows credit card updates by Subscriber)
+ [Public Signup Pages payment settings](https://maxio-chargify.zendesk.com/hc/en-us/articles/5405267754381-Individual-Page-Settings#credit-card-settings)

## Create a Payment Profile with a Chargify.js token

```json
{
  "payment_profile": {
    "customer_id": 1036,
    "chargify_token": "tok_w68qcpnftyv53jk33jv6wk3w"
  }
}
```

## Active Payment Methods

Creating a new payment profile for a Customer via the API will not make that Payment Profile current for any of the Customer’s Subscriptions. In order to utilize the payment profile as the default, it must be set as the default payment profile for the subscription or subscription group.

## Requirements

Either the full_number, expiration_month, and expiration_year or if you have an existing vault_token from your gateway, that vault_token and the current_vault are required.
Passing in the vault_token and current_vault are only allowed when creating a new payment profile.

### Taxable Subscriptions

If your subscriber pays taxes on their purchased product, and you are attempting to create or update the `payment_profile`, complete address information is required. For information on required address formatting to allow your subscriber to be taxed, please see our documentation [here](https://developers.chargify.com/docs/developer-docs/d2e9e34db740e-signups#taxes)

## BraintreeBlue

Some merchants use Braintree JavaScript libraries directly and then pass `payment_method_nonce` and/or `paypal_email` to create a payment profile. This implementation is deprecated and does not handle 3D Secure.  Instead, we have provided [Chargify.js](https://developers.chargify.com/docs/developer-docs/ZG9jOjE0NjAzNDI0-overview) which is continuously improved and supports Credit Cards (along with 3D Secure), PayPal and ApplePay payment types.

## GoCardless

For more information on GoCardless, please view the following resources:

+ [Full documentation on GoCardless](https://maxio-chargify.zendesk.com/hc/en-us/articles/5404501889677)

+ [Using Chargify.js with GoCardless - minimal example](https://developers.chargify.com/docs/developer-docs/ZG9jOjE0NjAzNDIy-examples#minimal-example-with-direct-debit-gocardless-gateway)

+ [Using Chargify.js with GoCardless - full example](https://developers.chargify.com/docs/developer-docs/ZG9jOjE0NjAzNDIy-examples#full-example-with-direct-debit-gocardless-gateway)

### GoCardless with Local Bank Details

Following examples create customer, bank account and mandate in GoCardless:

```json
{
  "payment_profile": {
    "customer_id": "Valid-Customer-ID",
    "bank_name": "Royal Bank of France",
    "bank_account_number": "0000000",
    "bank_routing_number": "0003",
    "bank_branch_code": "00006",
    "payment_type": "bank_account",
    "billing_address": "20 Place de la Gare",
    "billing_city": "Colombes",
    "billing_state": "Île-de-France",
    "billing_zip": "92700",
    "billing_country": "FR"
  }
}
```

### GoCardless with IBAN

```json
{
  "payment_profile": {
    "customer_id": "24907598",
    "bank_name": "French Bank",
    "bank_iban": "FR1420041010050500013M02606",
    "payment_type": "bank_account",
    "billing_address": "20 Place de la Gare",
    "billing_city": "Colombes",
    "billing_state": "Île-de-France",
    "billing_zip": "92700",
    "billing_country": "FR"
  }
}
```

### Importing GoCardless

If the customer, bank account, and mandate already exist in GoCardless, a payment profile can be created by using the IDs. In order to create masked versions of `bank_account_number` and `bank_routing_number` that are used to display within Chargify Admin UI, you can pass the last four digits for this fields which then will be saved in this form `XXXX[four-provided-digits]`.

```json
{
  "payment_profile": {
    "customer_id": "24907598",
    "customer_vault_token": [Existing GoCardless Customer ID]
    "vault_token": [Existing GoCardless Mandate ID],
    "current_vault": "gocardless",
    "bank_name": "French Bank",
    "bank_account_number": [Last Four Of The Existing Account Number or IBAN if applicable],
    "bank_routing_number": [Last Four Of The Existing Routing Number],
    "payment_type": "bank_account",
    "billing_address": "20 Place de la Gare",
    "billing_city": "Colombes",
    "billing_state": "Île-de-France",
    "billing_zip": "92700",
    "billing_country": "FR"
  }
}
```

## SEPA Direct Debit

For more information on Stripe SEPA Direct Debit, please view the following resources:

+ [Full documentation on Stripe SEPA Direct Debit](https://maxio-chargify.zendesk.com/hc/en-us/articles/5405050826765-Stripe-SEPA-and-BECS-Direct-Debit)

+ [Using Chargify.js with Stripe Direct Debit - minimal example](https://developers.chargify.com/docs/developer-docs/ZG9jOjE0NjAzNDIy-examples#minimal-example-with-sepa-or-becs-direct-debit-stripe-gateway)

+ [Using Chargify.js with Stripe Direct Debit - full example](https://developers.chargify.com/docs/developer-docs/ZG9jOjE0NjAzNDIy-examples#full-example-with-sepa-direct-debit-stripe-gateway)

### Stripe SEPA Direct Debit Payment Profiles

The following example creates a customer, bank account and mandate in Stripe:

```json
{
  "payment_profile": {
    "customer_id": "24907598",
    "bank_name": "Deutsche bank",
    "bank_iban": "DE89370400440532013000",
    "payment_type": "bank_account",
    "billing_address": "Test",
    "billing_city": "Berlin",
    "billing_state": "Brandenburg",
    "billing_zip": "12345",
    "billing_country": "DE"
  }
}
```

## Stripe BECS Direct Debit

For more information on Stripe BECS Direct Debit, please view the following resources:

+ [Full documentation on Stripe BECS Direct Debit](https://maxio-chargify.zendesk.com/hc/en-us/articles/5405050826765-Stripe-SEPA-and-BECS-Direct-Debit)

+ [Using Chargify.js with Stripe BECS Direct Debit - minimal example](https://developers.chargify.com/docs/developer-docs/ZG9jOjE0NjAzNDIy-examples#minimal-example-with-sepa-or-becs-direct-debit-stripe-gateway)

+ [Using Chargify.js with Stripe BECS Direct Debit - full example](https://developers.chargify.com/docs/developer-docs/ZG9jOjE0NjAzNDIy-examples#full-example-with-sepa-direct-debit-stripe-gateway)

### Stripe BECS Direct Debit Payment Profiles

The following example creates a customer, bank account and mandate in Stripe:

```json
{
  "payment_profile": {
    "customer_id": "24907598",
    "bank_name": "Australian bank",
    "bank_branch_code": "000000",
    "bank_account_number": "000123456"
    "payment_type": "bank_account",
    "billing_address": "Test",
    "billing_city": "Stony Rise",
    "billing_state": "Tasmania",
    "billing_zip": "12345",
    "billing_country": "AU"
  }
}
```

## 3D Secure - Checkout

It may happen that a payment needs 3D Secure Authentication when the payment profile is created; this is referred to in our help docs as a [post-authentication flow](https://maxio-chargify.zendesk.com/hc/en-us/articles/5405177432077#psd2-flows-pre-authentication-and-post-authentication). The server returns `422 Unprocessable Entity` in this case with the following response:

```json
{
    "jsonapi": {
        "version": "1.0"
    },
    "errors": [
        {
            "title": "This card requires 3DSecure verification.",
            "detail": "This card requires 3D secure authentication. Redirect the customer to the URL from the action_link attribute to authenticate. Attach callback_url param to this URL if you want to be notified about the result of 3D Secure authentication. Attach redirect_url param to this URL if you want to redirect a customer back to your page after 3D Secure authentication. Example: https://checkout-test.chargifypay.test/3d-secure/checkout/pay_uerzhsxd5uhkbodx5jhvkg6yeu?one_time_token_id=93&callback_url=http://localhost:4000&redirect_url=https://yourpage.com will do a POST request to https://localhost:4000 after credit card is authenticated and will redirect a customer to https://yourpage.com after 3DS authentication.",
            "links": {
                "action_link": "https://checkout-test.chargifypay.test/3d-secure/checkout/pay_uerzhsxd5uhkbodx5jhvkg6yeu?one_time_token_id=93"
            }
        }
    ]
}
```

To let the customer go through 3D Secure Authentication, they need to be redirected to the URL specified in `action_link`.
Optionally, you can specify `callback_url` parameter in the `action_link` URL if you’d like to be notified about the result of 3D Secure Authentication. The `callback_url` will return the following information:

- whether the authentication was successful (`success`)
- the payment profile ID (`payment_profile_id`)

Lastly, you can also specify a `redirect_url` parameter within the `action_link` URL if you’d like to redirect a customer back to your site.

It is not possible to use `action_link` in an iframe inside a custom application. You have to redirect the customer directly to the `action_link`, then, to be notified about the result, use `redirect_url` or `callback_url`.

The final URL that you send a customer to complete 3D Secure may resemble the following, where the first half is the `action_link` and the second half contains a `redirect_url` and `callback_url`: `https://checkout-test.chargifypay.test/3d-secure/checkout/pay_uerzhsxd5uhkbodx5jhvkg6yeu?one_time_token_id=93&callback_url=http://localhost:4000&redirect_url=https://yourpage.com`

### Example Redirect Flow

You may wish to redirect customers to different pages depending on whether their SCA was performed successfully. Here's an example flow to use as a reference:

1. Create a payment profile via API; it requires 3DS
2. You receive a `action_link` in the response.
3. Use this `action_link` to, for example, connect with your internal resources or generate a session_id
4. Include 1 of those attributes inside the `callback_url` and `redirect_url` to be aware which “session” this applies to
5. Redirect the customer to the `action_link` with `callback_url` and `redirect_url` applied
6. After the customer finishes 3DS authentication, we let you know the result by making a request to applied `callback_url`.
7. After that, we redirect the customer to the `redirect_url`; at this point the result of authentication is known
8. Optionally, you can use the applied "msg" param in the `redirect_url` to determine whether it was successful or not

```java
CreatePaymentProfileResponse createPaymentProfile(
    final CreatePaymentProfileRequest body)
```

## Parameters

| Parameter | Type | Tags | Description |
|  --- | --- | --- | --- |
| `body` | [`CreatePaymentProfileRequest`](../../doc/models/create-payment-profile-request.md) | Body, Optional | When following the IBAN or the Local Bank details examples, a customer, bank account and mandate will be created in your current vault. If the customer, bank account, and mandate already exist in your vault, follow the Import example to link the payment profile into Chargify. |

## Response Type

[`CreatePaymentProfileResponse`](../../doc/models/create-payment-profile-response.md)

## Example Usage

```java
CreatePaymentProfileRequest body = new CreatePaymentProfileRequest.Builder(
    new CreatePaymentProfile.Builder()
        .paymentType(PaymentType.BANK_ACCOUNT)
        .customerId(123)
        .bankName("Best Bank")
        .bankRoutingNumber("021000089")
        .bankAccountNumber("111111111111")
        .bankAccountType("checking")
        .bankAccountHolderType("business")
        .build()
)
.build();

try {
    CreatePaymentProfileResponse result = paymentProfilesController.createPaymentProfile(body);
    System.out.println(result);
} catch (ApiException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
}
```

## Example Response *(as JSON)*

```json
{
  "payment_profile": {
    "first_name": "Jessica",
    "last_name": "Test",
    "card_type": "visa",
    "expiration_month": 10,
    "expiration_year": 2018,
    "customer_id": 19195410,
    "current_vault": "bogus",
    "vault_token": "1",
    "billing_address": "123 Main St.",
    "billing_city": "Boston",
    "billing_state": "MA",
    "billing_zip": "02120",
    "billing_country": "US",
    "customer_vault_token": null,
    "billing_address_2": null,
    "payment_type": "credit_card",
    "site_gateway_setting_id": 1,
    "gateway_handle": "handle"
  }
}
```

## Errors

| HTTP Status Code | Error Description | Exception Class |
|  --- | --- | --- |
| 404 | Not Found | `ApiException` |


# List Payment Profiles

This method will return all of the active `payment_profiles` for a Site, or for one Customer within a site.  If no payment profiles are found, this endpoint will return an empty array, not a 404.

```java
List<ListPaymentProfilesResponse> listPaymentProfiles(
    final ListPaymentProfilesInput input)
```

## Parameters

| Parameter | Type | Tags | Description |
|  --- | --- | --- | --- |
| `page` | `Integer` | Query, Optional | Result records are organized in pages. By default, the first page of results is displayed. The page parameter specifies a page number of results to fetch. You can start navigating through the pages to consume the results. You do this by passing in a page parameter. Retrieve the next page by adding ?page=2 to the query string. If there are no results to return, then an empty result set will be returned.<br>Use in query `page=1`.<br>**Default**: `1`<br>**Constraints**: `>= 1` |
| `perPage` | `Integer` | Query, Optional | This parameter indicates how many records to fetch in each request. Default value is 20. The maximum allowed values is 200; any per_page value over 200 will be changed to 200.<br>Use in query `per_page=200`.<br>**Default**: `20`<br>**Constraints**: `<= 200` |
| `customerId` | `Integer` | Query, Optional | The ID of the customer for which you wish to list payment profiles |

## Response Type

[`List<ListPaymentProfilesResponse>`](../../doc/models/list-payment-profiles-response.md)

## Example Usage

```java
ListPaymentProfilesInput listPaymentProfilesInput = new ListPaymentProfilesInput.Builder()
    .page(2)
    .perPage(50)
    .build();

try {
    List<ListPaymentProfilesResponse> result = paymentProfilesController.listPaymentProfiles(listPaymentProfilesInput);
    System.out.println(result);
} catch (ApiException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
}
```

## Example Response *(as JSON)*

```json
[
  {
    "payment_profile": {
      "id": 10089892,
      "first_name": "Chester",
      "last_name": "Tester",
      "customer_id": 14543792,
      "current_vault": "bogus",
      "vault_token": "0011223344",
      "billing_address": "456 Juniper Court",
      "billing_city": "Boulder",
      "billing_state": "CO",
      "billing_zip": "80302",
      "billing_country": "US",
      "customer_vault_token": null,
      "billing_address_2": "",
      "bank_name": "Bank of Kansas City",
      "masked_bank_routing_number": "XXXX6789",
      "masked_bank_account_number": "XXXX3344",
      "bank_account_type": "checking",
      "bank_account_holder_type": "personal",
      "payment_type": "bank_account",
      "site_gateway_setting_id": 1,
      "gateway_handle": "handle"
    }
  },
  {
    "payment_profile": {
      "id": 10188522,
      "first_name": "Frankie",
      "last_name": "Tester",
      "customer_id": 14543712,
      "current_vault": "bogus",
      "vault_token": "123456789",
      "billing_address": "123 Montana Way",
      "billing_city": "Los Angeles",
      "billing_state": "CA",
      "billing_zip": "90210",
      "billing_country": "US",
      "customer_vault_token": null,
      "billing_address_2": "",
      "bank_name": "Bank of Kansas City",
      "masked_bank_routing_number": "XXXX6789",
      "masked_bank_account_number": "XXXX6789",
      "bank_account_type": "checking",
      "bank_account_holder_type": "personal",
      "payment_type": "bank_account",
      "site_gateway_setting_id": 1,
      "gateway_handle": "handle"
    }
  }
]
```


# Read Payment Profile

Using the GET method you can retrieve a Payment Profile identified by its unique ID.

Please note that a different JSON object will be returned if the card method on file is a bank account.

### Response for Bank Account

Example response for Bank Account:

```
{
  "payment_profile": {
    "id": 10089892,
    "first_name": "Chester",
    "last_name": "Tester",
    "customer_id": 14543792,
    "current_vault": "bogus",
    "vault_token": "0011223344",
    "billing_address": "456 Juniper Court",
    "billing_city": "Boulder",
    "billing_state": "CO",
    "billing_zip": "80302",
    "billing_country": "US",
    "customer_vault_token": null,
    "billing_address_2": "",
    "bank_name": "Bank of Kansas City",
    "masked_bank_routing_number": "XXXX6789",
    "masked_bank_account_number": "XXXX3344",
    "bank_account_type": "checking",
    "bank_account_holder_type": "personal",
    "payment_type": "bank_account",
    "site_gateway_setting_id": 1,
    "gateway_handle": null
  }
}
```

```java
ReadPaymentProfileResponse readPaymentProfile(
    final String paymentProfileId)
```

## Parameters

| Parameter | Type | Tags | Description |
|  --- | --- | --- | --- |
| `paymentProfileId` | `String` | Template, Required | The Chargify id of the payment profile |

## Response Type

[`ReadPaymentProfileResponse`](../../doc/models/read-payment-profile-response.md)

## Example Usage

```java
String paymentProfileId = "payment_profile_id2";

try {
    ReadPaymentProfileResponse result = paymentProfilesController.readPaymentProfile(paymentProfileId);
    System.out.println(result);
} catch (ApiException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
}
```

## Example Response *(as JSON)*

```json
{
  "payment_profile": {
    "id": 10088716,
    "first_name": "Test",
    "last_name": "Subscription",
    "masked_card_number": "XXXX-XXXX-XXXX-1",
    "card_type": "bogus",
    "expiration_month": 1,
    "expiration_year": 2022,
    "customer_id": 14543792,
    "current_vault": "bogus",
    "vault_token": "1",
    "billing_address": "123 Montana Way",
    "billing_city": "Billings",
    "billing_state": "MT",
    "billing_zip": "59101",
    "billing_country": "US",
    "customer_vault_token": null,
    "billing_address_2": "",
    "payment_type": "credit_card",
    "site_gateway_setting_id": 1,
    "gateway_handle": null
  }
}
```


# Update Payment Profile

## Partial Card Updates

In the event that you are using the Authorize.net, Stripe, Cybersource, Forte or Braintree Blue payment gateways, you can update just the billing and contact information for a payment method. Note the lack of credit-card related data contained in the JSON payload.

In this case, the following JSON is acceptable:

```
{
  "payment_profile": {
    "first_name": "Kelly",
    "last_name": "Test",
    "billing_address": "789 Juniper Court",
    "billing_city": "Boulder",
    "billing_state": "CO",
    "billing_zip": "80302",
    "billing_country": "US",
    "billing_address_2": null
  }
}
```

The result will be that you have updated the billing information for the card, yet retained the original card number data.

## Specific notes on updating payment profiles

- Merchants with **Authorize.net**, **Cybersource**, **Forte**, **Braintree Blue** or **Stripe** as their payment gateway can update their Customer’s credit cards without passing in the full credit card number and CVV.

- If you are using **Authorize.net**, **Cybersource**, **Forte**, **Braintree Blue** or **Stripe**, Chargify will ignore the credit card number and CVV when processing an update via the API, and attempt a partial update instead. If you wish to change the card number on a payment profile, you will need to create a new payment profile for the given customer.

- A Payment Profile cannot be updated with the attributes of another type of Payment Profile. For example, if the payment profile you are attempting to update is a credit card, you cannot pass in bank account attributes (like `bank_account_number`), and vice versa.

- Updating a payment profile directly will not trigger an attempt to capture a past-due balance. If this is the intent, update the card details via the Subscription instead.

- If you are using Authorize.net or Stripe, you may elect to manually trigger a retry for a past due subscription after a partial update.

```java
UpdatePaymentProfileResponse updatePaymentProfile(
    final String paymentProfileId,
    final UpdatePaymentProfileRequest body)
```

## Parameters

| Parameter | Type | Tags | Description |
|  --- | --- | --- | --- |
| `paymentProfileId` | `String` | Template, Required | The Chargify id of the payment profile |
| `body` | [`UpdatePaymentProfileRequest`](../../doc/models/update-payment-profile-request.md) | Body, Optional | - |

## Response Type

[`UpdatePaymentProfileResponse`](../../doc/models/update-payment-profile-response.md)

## Example Usage

```java
String paymentProfileId = "payment_profile_id2";
UpdatePaymentProfileRequest body = new UpdatePaymentProfileRequest.Builder(
    new UpdatePaymentProfile.Builder()
        .firstName("Graham")
        .lastName("Test")
        .fullNumber("4111111111111111")
        .cardType(CardType.MASTER)
        .expirationMonth("04")
        .expirationYear("2030")
        .currentVault(CurrentVault.BOGUS)
        .billingAddress("456 Juniper Court")
        .billingCity("Boulder")
        .billingState("CO")
        .billingZip("80302")
        .billingCountry("US")
        .billingAddress2("billing_address_22")
        .build()
)
.build();

try {
    UpdatePaymentProfileResponse result = paymentProfilesController.updatePaymentProfile(paymentProfileId, body);
    System.out.println(result);
} catch (ApiException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
}
```

## Example Response *(as JSON)*

```json
{
  "payment_profile": {
    "id": 10088716,
    "first_name": "Test",
    "last_name": "Subscription",
    "masked_card_number": "XXXX-XXXX-XXXX-1",
    "card_type": "bogus",
    "expiration_month": 1,
    "expiration_year": 2022,
    "customer_id": 14543792,
    "current_vault": "bogus",
    "vault_token": "1",
    "billing_address": "123 Montana Way",
    "billing_city": "Billings",
    "billing_state": "MT",
    "billing_zip": "59101",
    "billing_country": "US",
    "customer_vault_token": null,
    "billing_address_2": "",
    "payment_type": "credit_card",
    "site_gateway_setting_id": 1,
    "gateway_handle": null
  }
}
```


# Delete Unused Payment Profile

Deletes an unused payment profile.

If the payment profile is in use by one or more subscriptions or groups, a 422 and error message will be returned.

```java
Void deleteUnusedPaymentProfile(
    final String paymentProfileId)
```

## Parameters

| Parameter | Type | Tags | Description |
|  --- | --- | --- | --- |
| `paymentProfileId` | `String` | Template, Required | The Chargify id of the payment profile |

## Response Type

`void`

## Example Usage

```java
String paymentProfileId = "payment_profile_id2";

try {
    paymentProfilesController.deleteUnusedPaymentProfile(paymentProfileId);
} catch (ApiException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
}
```

## Errors

| HTTP Status Code | Error Description | Exception Class |
|  --- | --- | --- |
| 422 | Unprocessable Entity (WebDAV) | [`ErrorListResponseException`](../../doc/models/error-list-response-exception.md) |


# Delete Subscriptions Payment Profile

This will delete a payment profile belonging to the customer on the subscription.

+ If the customer has multiple subscriptions, the payment profile will be removed from all of them.

+ If you delete the default payment profile for a subscription, you will need to specify another payment profile to be the default through the api, or either prompt the user to enter a card in the billing portal or on the self-service page, or visit the Payment Details tab on the subscription in the Admin UI and use the “Add New Credit Card” or “Make Active Payment Method” link, (depending on whether there are other cards present).

```java
Void deleteSubscriptionsPaymentProfile(
    final String subscriptionId,
    final String paymentProfileId)
```

## Parameters

| Parameter | Type | Tags | Description |
|  --- | --- | --- | --- |
| `subscriptionId` | `String` | Template, Required | The Chargify id of the subscription |
| `paymentProfileId` | `String` | Template, Required | The Chargify id of the payment profile |

## Response Type

`void`

## Example Usage

```java
String subscriptionId = "subscription_id0";
String paymentProfileId = "payment_profile_id2";

try {
    paymentProfilesController.deleteSubscriptionsPaymentProfile(subscriptionId, paymentProfileId);
} catch (ApiException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
}
```


# Verify Bank Account

Submit the two small deposit amounts the customer received in their bank account in order to verify the bank account. (Stripe only)

```java
BankAccountResponse verifyBankAccount(
    final int bankAccountId,
    final BankAccountVerificationRequest body)
```

## Parameters

| Parameter | Type | Tags | Description |
|  --- | --- | --- | --- |
| `bankAccountId` | `int` | Template, Required | Identifier of the bank account in the system. |
| `body` | [`BankAccountVerificationRequest`](../../doc/models/bank-account-verification-request.md) | Body, Optional | - |

## Response Type

[`BankAccountResponse`](../../doc/models/bank-account-response.md)

## Example Usage

```java
int bankAccountId = 252;
BankAccountVerificationRequest body = new BankAccountVerificationRequest.Builder(
    new BankAccountVerification.Builder()
        .deposit1InCents(32)
        .deposit2InCents(45)
        .build()
)
.build();

try {
    BankAccountResponse result = paymentProfilesController.verifyBankAccount(bankAccountId, body);
    System.out.println(result);
} catch (ApiException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
}
```

## Example Response *(as JSON)*

```json
{
  "payment_profile": {
    "id": 10089892,
    "first_name": "Chester",
    "last_name": "Tester",
    "customer_id": 14543792,
    "current_vault": "stripe_connect",
    "vault_token": "cus_0123abc456def",
    "billing_address": "456 Juniper Court",
    "billing_city": "Boulder",
    "billing_state": "CO",
    "billing_zip": "80302",
    "billing_country": "US",
    "customer_vault_token": null,
    "billing_address_2": "",
    "bank_name": "Bank of Kansas City",
    "masked_bank_routing_number": "XXXX6789",
    "masked_bank_account_number": "XXXX3344",
    "bank_account_type": "checking",
    "bank_account_holder_type": "personal",
    "payment_type": "bank_account"
  }
}
```

## Errors

| HTTP Status Code | Error Description | Exception Class |
|  --- | --- | --- |
| 404 | Not Found | `ApiException` |
| 422 | Unprocessable Entity (WebDAV) | [`ErrorListResponseException`](../../doc/models/error-list-response-exception.md) |


# Delete Subscription Group Payment Profile

This will delete a Payment Profile belonging to a Subscription Group.

**Note**: If the Payment Profile belongs to multiple Subscription Groups and/or Subscriptions, it will be removed from all of them.

```java
Void deleteSubscriptionGroupPaymentProfile(
    final String uid,
    final String paymentProfileId)
```

## Parameters

| Parameter | Type | Tags | Description |
|  --- | --- | --- | --- |
| `uid` | `String` | Template, Required | The uid of the subscription group |
| `paymentProfileId` | `String` | Template, Required | The Chargify id of the payment profile |

## Response Type

`void`

## Example Usage

```java
String uid = "uid0";
String paymentProfileId = "payment_profile_id2";

try {
    paymentProfilesController.deleteSubscriptionGroupPaymentProfile(uid, paymentProfileId);
} catch (ApiException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
}
```


# Update Subscription Default Payment Profile

This will change the default payment profile on the subscription to the existing payment profile with the id specified.

You must elect to change the existing payment profile to a new payment profile ID in order to receive a satisfactory response from this endpoint.

```java
PaymentProfileResponse updateSubscriptionDefaultPaymentProfile(
    final String subscriptionId,
    final int paymentProfileId)
```

## Parameters

| Parameter | Type | Tags | Description |
|  --- | --- | --- | --- |
| `subscriptionId` | `String` | Template, Required | The Chargify id of the subscription |
| `paymentProfileId` | `int` | Template, Required | The Chargify id of the payment profile |

## Response Type

[`PaymentProfileResponse`](../../doc/models/payment-profile-response.md)

## Example Usage

```java
String subscriptionId = "subscription_id0";
int paymentProfileId = 198;

try {
    PaymentProfileResponse result = paymentProfilesController.updateSubscriptionDefaultPaymentProfile(subscriptionId, paymentProfileId);
    System.out.println(result);
} catch (ApiException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
}
```

## Example Response *(as JSON)*

```json
{
  "payment_profile": {
    "id": 10211899,
    "first_name": "Amelia",
    "last_name": "Example",
    "masked_card_number": "XXXX-XXXX-XXXX-1",
    "card_type": "bogus",
    "expiration_month": 2,
    "expiration_year": 2018,
    "customer_id": 14399371,
    "current_vault": "bogus",
    "vault_token": "1",
    "billing_address": "",
    "billing_city": "",
    "billing_state": "",
    "billing_zip": "",
    "billing_country": "",
    "customer_vault_token": null,
    "billing_address_2": "",
    "payment_type": "credit_card",
    "site_gateway_setting_id": 1,
    "gateway_handle": null
  }
}
```

## Errors

| HTTP Status Code | Error Description | Exception Class |
|  --- | --- | --- |
| 422 | Unprocessable Entity (WebDAV) | [`ErrorListResponseException`](../../doc/models/error-list-response-exception.md) |


# Update Subscription Group Default Payment Profile

This will change the default payment profile on the subscription group to the existing payment profile with the id specified.

You must elect to change the existing payment profile to a new payment profile ID in order to receive a satisfactory response from this endpoint.

The new payment profile must belong to the subscription group's customer, otherwise you will receive an error.

```java
PaymentProfileResponse updateSubscriptionGroupDefaultPaymentProfile(
    final String uid,
    final String paymentProfileId)
```

## Parameters

| Parameter | Type | Tags | Description |
|  --- | --- | --- | --- |
| `uid` | `String` | Template, Required | The uid of the subscription group |
| `paymentProfileId` | `String` | Template, Required | The Chargify id of the payment profile |

## Response Type

[`PaymentProfileResponse`](../../doc/models/payment-profile-response.md)

## Example Usage

```java
String uid = "uid0";
String paymentProfileId = "payment_profile_id2";

try {
    PaymentProfileResponse result = paymentProfilesController.updateSubscriptionGroupDefaultPaymentProfile(uid, paymentProfileId);
    System.out.println(result);
} catch (ApiException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
}
```

## Example Response *(as JSON)*

```json
{
  "payment_profile": {
    "id": 10211899,
    "first_name": "Amelia",
    "last_name": "Example",
    "masked_card_number": "XXXX-XXXX-XXXX-1",
    "card_type": "bogus",
    "expiration_month": 2,
    "expiration_year": 2018,
    "customer_id": 14399371,
    "current_vault": "bogus",
    "vault_token": "1",
    "billing_address": "",
    "billing_city": "",
    "billing_state": "",
    "billing_zip": "",
    "billing_country": "",
    "customer_vault_token": null,
    "billing_address_2": "",
    "payment_type": "credit_card",
    "site_gateway_setting_id": 1,
    "gateway_handle": null
  }
}
```

## Errors

| HTTP Status Code | Error Description | Exception Class |
|  --- | --- | --- |
| 422 | Unprocessable Entity (WebDAV) | [`ErrorListResponseException`](../../doc/models/error-list-response-exception.md) |


# Read One Time Token

One Time Tokens aka Chargify Tokens house the credit card or ACH (Authorize.Net or Stripe only) data for a customer.

You can use One Time Tokens while creating a subscription or payment profile instead of passing all bank account or credit card data directly to a given API endpoint.

To obtain a One Time Token you have to use [chargify.js](https://developers.chargify.com/docs/developer-docs/ZG9jOjE0NjAzNDI0-overview).

```java
GetOneTimeTokenRequest readOneTimeToken(
    final String chargifyToken)
```

## Parameters

| Parameter | Type | Tags | Description |
|  --- | --- | --- | --- |
| `chargifyToken` | `String` | Template, Required | Chargify Token |

## Response Type

[`GetOneTimeTokenRequest`](../../doc/models/get-one-time-token-request.md)

## Example Usage

```java
String chargifyToken = "chargify_token8";

try {
    GetOneTimeTokenRequest result = paymentProfilesController.readOneTimeToken(chargifyToken);
    System.out.println(result);
} catch (ApiException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
}
```

## Errors

| HTTP Status Code | Error Description | Exception Class |
|  --- | --- | --- |
| 404 | Not Found | [`ErrorListResponseException`](../../doc/models/error-list-response-exception.md) |


# Send Request Update Payment Email

You can send a "request payment update" email to the customer associated with the subscription.

If you attempt to send a "request payment update" email more than five times within a 30-minute period, you will receive a `422` response with an error message in the body. This error message will indicate that the request has been rejected due to excessive attempts, and will provide instructions on how to resubmit the request.

Additionally, if you attempt to send a "request payment update" email for a subscription that does not exist, you will receive a `404` error response. This error message will indicate that the subscription could not be found, and will provide instructions on how to correct the error and resubmit the request.

These error responses are designed to prevent excessive or invalid requests, and to provide clear and helpful information to users who encounter errors during the request process.

```java
Void sendRequestUpdatePaymentEmail(
    final String subscriptionId)
```

## Parameters

| Parameter | Type | Tags | Description |
|  --- | --- | --- | --- |
| `subscriptionId` | `String` | Template, Required | The Chargify id of the subscription |

## Response Type

`void`

## Example Usage

```java
String subscriptionId = "subscription_id0";

try {
    paymentProfilesController.sendRequestUpdatePaymentEmail(subscriptionId);
} catch (ApiException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
}
```

## Errors

| HTTP Status Code | Error Description | Exception Class |
|  --- | --- | --- |
| 404 | Not Found | `ApiException` |
| 422 | Unprocessable Entity (WebDAV) | [`ErrorListResponseException`](../../doc/models/error-list-response-exception.md) |
