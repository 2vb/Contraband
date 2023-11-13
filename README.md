
# 🚫 Contraband 🚫

Easily keep players from having banned items in their inventory!



## Configuration
By default, operator only blocks/items will be removed from inventories. You can add or remove items with `/contraband`

### Default Config
```yaml
enabled: true

# How often players' inventories will be checked for contraband.
minutes-between-checks: 2

messages:
  no-permissions: "&7[&c✖&7] &cYou do not have permission to run this command."
  detect: "&7[&c✖&7] &cContraband was detected and removed."
  invalid: "&7[&c✖&7] &cInvalid item. Please provide a valid item ID."
  commands:
    add: "&7[&c✖&7] &a%item% is now contraband."
    remove: "&7[&c✖&7] &c%item% is no longer contraband."
    usage: "&7[&c✖&7] /contraband <add/remove/toggle>"
    toggle:
      enable: "&7[&c✖&7] &aContraband will now be removed from players."
      disable: "&7[&c✖&7] &cContraband will no longer be removed from players."

```

