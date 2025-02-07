Feature: Parcel Tracking

  Scenario Outline: Verify parcel status
    Given I navigate to the InPost tracking page
    When I search for parcel "<parcel_number>"
    Then I should see the status "<expected_status>"

    Examples:
      | parcel_number            | expected_status     |
      | 520113014230722029585646 | Delivered           |
      | 520107010449991105638120 | Passed for delivery |
      | 523000016696115042036670 | Label nullified     |
      | 520000011395200025754311 | Delivered           |