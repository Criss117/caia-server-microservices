"use client";

import Link from "next/link";
import { ChevronDown, NotepadText, Pencil } from "lucide-react";

import { Button } from "@/core/shared/components/ui/button";
import { ROUTES } from "@/core/shared/lib/constants/routes";
import MembersListDialog from "@/core/dashboard/presentation/components/members-list-dialog";
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from "@/core/shared/components/ui/popover";
import { Separator } from "@/core/shared/components/ui/separator";
import SearchUserDialog from "@/core/users/presentation/components/search-users/search-user-dialog";
import { SessionProvider } from "next-auth/react";
import { ConferencesDto } from "../../data/dto/conference.dto";
import useCurrentConferenceState from "../../application/state/current-conference.state";
import { useEffect } from "react";

interface Props {
  conference: ConferencesDto;
}

const ConferenceActions = ({ conference }: Props) => {
  const { setConference } = useCurrentConferenceState();
  const editConferenceHref = ROUTES.DASHBOARD.EDIT_CONFERENCE.replace(
    "[conferenceId]",
    conference.slug
  );

  const papersList = ROUTES.DASHBOARD.PAPERS_LIST.replace(
    "[conferenceId]",
    conference.slug
  );

  useEffect(() => {
    setConference(conference);
  }, [conference, setConference]);

  return (
    <SessionProvider>
      <Popover>
        <PopoverTrigger asChild>
          <Button className="bg-lightprimary-200 gap-x-2">
            <p>Acciones</p>
            <ChevronDown className="w-5 h-5" />
          </Button>
        </PopoverTrigger>
        <PopoverContent className="space-y-5">
          <Separator />
          <MembersListDialog />
          <Separator />
          <Button variant="outline" asChild>
            <Link
              href={editConferenceHref}
              className="space-x-2 w-full justify-between"
            >
              <Pencil className="w-5 h-5" />
              <p>Editar conferencia</p>
            </Link>
          </Button>
          <Button variant="outline" asChild>
            <Link
              href={papersList}
              className="space-x-2 w-full justify-between"
            >
              <NotepadText className="w-5 h-5" />
              <p>Ver papers</p>
            </Link>
          </Button>
          <SearchUserDialog />
          <Separator />
          <Button variant="destructive" className="w-full">
            Eliminar conferencia
          </Button>
        </PopoverContent>
      </Popover>
    </SessionProvider>
  );
};

export default ConferenceActions;
