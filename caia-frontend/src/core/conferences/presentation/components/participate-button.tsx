"use client";

import Link from "next/link";
import { ChevronDown, SendHorizonal } from "lucide-react";

import { Button } from "@/core/shared/components/ui/button";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/core/shared/components/ui/dropdown-menu";
import { ROUTES } from "@/core/shared/lib/constants/routes";

interface Props {
  slug: string;
}

const ParticipateButton = ({ slug }: Props) => {
  const sendPaperUrl = ROUTES.DASHBOARD.SEND_PAPER.replace(
    "[conferenceId]",
    slug
  );

  return (
    <DropdownMenu>
      <DropdownMenuTrigger asChild>
        <Button className="bg-lightprimary-200 gap-x-2">
          <p>Participar</p>
          <ChevronDown className="w-5 h-5" />
        </Button>
      </DropdownMenuTrigger>
      <DropdownMenuContent>
        <DropdownMenuLabel>Participar en esta encuesta</DropdownMenuLabel>
        <DropdownMenuSeparator />
        <DropdownMenuItem>
          <Button asChild className="w-full justify-between" variant="outline">
            <Link href={sendPaperUrl}>
              <p>Enviar mi paper</p>
              <SendHorizonal className="w-5 h-5" />
            </Link>
          </Button>
        </DropdownMenuItem>
      </DropdownMenuContent>
    </DropdownMenu>
  );
};

export default ParticipateButton;
