
# List Invoices Response

## Structure

`ListInvoicesResponse`

## Fields

| Name | Type | Tags | Description | Getter | Setter |
|  --- | --- | --- | --- | --- | --- |
| `Invoices` | [`List<Invoice>`](../../doc/models/invoice.md) | Required | - | List<Invoice> getInvoices() | setInvoices(List<Invoice> invoices) |

## Example (as JSON)

```json
{
  "invoices": [
    {
      "id": 196,
      "uid": "uid6",
      "site_id": 122,
      "customer_id": 234,
      "subscription_id": 50
    }
  ]
}
```

