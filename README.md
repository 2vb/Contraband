
# 🚫 Contraband 🚫

Easily keep players from having banned items in their inventory!



## Installation

    1. Download this plugin from [link] and add it to your servers plugins folder!
    2. Restart your server!
    
## Configuration

You can configure this plugin with in game commands or with the `plugin.yml` file.

### Default Config
```yaml
enabled: true
items:
  - BEDROCK
  - BARRIER
messages:
  commands:
    add:
      success: "&a✔ %item% has been marked as contraband. ✔"
    remove:
      success: "&c✔ %item% has been unmarked as contraband. ✔"
    usage: "&7[&c✖&7] /contraband <add/list/remove>"
  pickup: "&c⚠ You picked up a disallowed item! This item has been removed from your inventory. ⚠"
  detect: "&c⚠ A disallowed item has been detected! This item has been removed from your inventory. ⚠"
  invalid: "&c✖ Invalid item. Please make sure you are providing a valid item. &c ✖"
```

