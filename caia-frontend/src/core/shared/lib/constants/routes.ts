import { CalendarCheck, Mail, Newspaper, Search } from "lucide-react";

export const ROUTES = {
  HOME: "/",
  LOGIN: "/account/login",
  SIGNUP: "/account/signup",
  CONFERENCE: "/conference",
  ACCOUNT_CREATED: "/account/signup/created",
  DASHBOARD: {
    ROOT: "/dashboard",
    CONFERENCES: "/dashboard/conferences",
    CREATE_CONFERENCES: "/dashboard/conferences/create",
    EDIT_CONFERENCE: "/dashboard/conferences/[conferenceId]/edit",
    OWN: "/dashboard/conferences/own",
    DISCOVER: "/dashboard/conferences",
    PAPERS: "/dashboard/papers",
    EDIT_PAPER: "/dashboard/papers/[paperId]/edit",
    PAPERS_LIST: "/dashboard/conferences/[conferenceId]/papers",
    PAPERS_NEW: "/dashboard/papers/new",
    INVITATIONS: "/dashboard/reviewer/invitations",
    ASSIGNED_PAPERS: "/dashboard/reviewer/papers",
    SEND_PAPER: "/dashboard/conferences/[conferenceId]/send-paper",
  },
} as const;

export const conferenceRoutes = [
  {
    name: "Conferencias",
    routes: [
      {
        name: "Mis Conferencias",
        path: ROUTES.DASHBOARD.OWN,
        Icon: CalendarCheck,
      },
      {
        name: "Descubrir conferencias",
        path: ROUTES.DASHBOARD.CONFERENCES,
        Icon: Search,
      },
    ],
  },
  {
    name: "Autor",
    routes: [
      {
        name: "Mis Papers",
        path: ROUTES.DASHBOARD.PAPERS,
        Icon: Newspaper,
      },
    ],
  },
  {
    name: "Revisor",
    routes: [
      {
        name: "Papers asignados(sin implementar)",
        path: ROUTES.DASHBOARD.ASSIGNED_PAPERS,
        Icon: Newspaper,
      },
      {
        name: "Invitaciones",
        path: ROUTES.DASHBOARD.INVITATIONS,
        Icon: Mail,
      },
    ],
  },
] as const;
