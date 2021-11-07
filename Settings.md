# Settings
## Auto-Approval
Controls aspects of auto-approval of editions

Type: `AUTO_APPROVAL`

### Properties: 
* `isEnabled`: Whether auto-approval is enabled (`boolean`)
### Example: 
```json
{
  "isEnabled": true,
  "settingsType": "AUTO_APPROVAL"
}
```
## Edition Creation
Type: `EDITION`

### Properties: 
* `editionSubjectTypes`: The default subjects to use for the edition, mandatory (array of [`FIRST_ARTICLE_TITLE` | `CAMPAIGN_TITLE`])
### Example: 
```json
{
  "editionSubjectTypes": [
    "CAMPAIGN_TITLE"
  ],
  "settingsType": "EDITION"
}
```
## Subscriber Confirmation
Controls aspects of subscriber confirmation

Type: `SUBSCRIBER_CONFIRMATION`

### Properties: 
* `isEnabled`: Whether subscriber confirmation is enabled, mandatory (`boolean`)
* `emailSubject`: The subject line for the confirmation email, mandatory when `isEnabled` is `true` (`string`)
### Example: 
```json
{
  "isEnabled": true,
  "emailSubject": "Email Subject",
  "settingsType": "SUBSCRIBER_CONFIRMATION"
}
```
