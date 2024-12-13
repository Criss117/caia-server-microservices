"use client";

import { Button } from "@/core/shared/components/ui/button";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/core/shared/components/ui/dialog";
import { Input } from "@/core/shared/components/ui/input";
import { Label } from "@/core/shared/components/ui/label";
import { Mail } from "lucide-react";
import useSearchUsers from "../../../application/hooks/use.search-users";
import SearchUsersTable from "./search-users-table";

const SearchUserDialog = () => {
  const { users, query, onChangeQuery } = useSearchUsers();

  return (
    <Dialog>
      <DialogTrigger asChild>
        <Button variant="outline" className="w-full justify-between">
          <Mail className="w-5 h-5" />
          <p>Invitar a un revisor</p>
        </Button>
      </DialogTrigger>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>Invitar a un revisor</DialogTitle>
          <DialogDescription>
            Invita a un revisor a la encuesta.
          </DialogDescription>
        </DialogHeader>
        <div>
          <Label htmlFor="query">
            <p>Busca por nombre o correo</p>
            <Input
              id="query"
              placeholder="Busca por nombre o correo"
              value={query}
              onChange={(e) => onChangeQuery(e.target.value)}
            />
          </Label>
        </div>
        <div>
          <SearchUsersTable users={users} />
        </div>
      </DialogContent>
    </Dialog>
  );
};

export default SearchUserDialog;
