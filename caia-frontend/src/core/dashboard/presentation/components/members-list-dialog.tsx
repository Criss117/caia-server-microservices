"use client";

import { Users } from "lucide-react";
import { Button } from "@/core/shared/components/ui/button";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/core/shared/components/ui/dialog";

const MembersListDialog = () => {
  return (
    <Dialog>
      <DialogTrigger asChild>
        <Button variant="outline" className="w-full justify-between">
          <Users className="w-5 h-5" />
          <p>Listado de miembros</p>
        </Button>
      </DialogTrigger>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>Lista de miembros</DialogTitle>
          <DialogDescription>
            <h3>Organizadores</h3>
            <h3>Autores</h3>
            <h3>Revisores</h3>
          </DialogDescription>
        </DialogHeader>
      </DialogContent>
    </Dialog>
  );
};

export default MembersListDialog;
