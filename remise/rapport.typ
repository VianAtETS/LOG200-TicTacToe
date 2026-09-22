#import "ets-report.typ": report, pts, ans, q, todo

#show: report.with(
  work-type:   "Laboratoire",
  course-code: "LOG320",
  course-name: "Structures de données et algorithmes",
  title:       "Jeu plateau — Phase 1 : Tic Tac Toe",
  students:    ("Prénom Nom (CODE12345678)",),
  semester:    "Automne 2026",
  group:       "01",
)

#todo[Compléter le nom, le code permanent et le groupe sur la page de titre.]

= Introduction

#q[Implémenter un agent intelligent capable de jouer au Tic Tac Toe à l'aide
des algorithmes Minimax et Alpha-Beta.]

= Implémentation

== Agent (`CPUPlayer`)

#raw(read("/src/CPUPlayer.java"), lang: "java", block: true)
