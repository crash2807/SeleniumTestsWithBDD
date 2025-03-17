Feature: Wybieranie telefonu z oferty T-Mobile

  Scenario: Dodanie telefonu do koszyka
    Given Użytkownik otwiera przeglądarkę i przechodzi na stronę "https://www.t-mobile.pl/"
    Then Użytkownik jest na stronie głównej
    When Wybiera sekcję Urządzenia i wybiera Smartwatche w sekcji Bez abonamentu
    And Wybiera pierwszy smartwatch z listy
    And Dodaje smartwatch do koszyka
    Then Koszyk zawiera wybrany telefon z odpowiednią ceną i ratą
    Then Użytkownik wraca na stronę główną
    Then Użytkownik jest na stronie głównej
    Then Ikona koszyka uległa zmianie
