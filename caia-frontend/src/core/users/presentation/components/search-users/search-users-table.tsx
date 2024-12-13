import SendInvitationButton from "@/core/reviewer/presentation/components/send-invitation-button";
import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/core/shared/components/ui/table";
import { UserEntity } from "@/core/users/data/entities/user.entity";

interface Props {
  users: UserEntity[];
}

const SearchUsersTable = ({ users }: Props) => {
  return (
    <Table>
      <TableCaption>Lista de usuarios</TableCaption>
      <TableHeader>
        <TableRow>
          <TableHead className="">Nombre</TableHead>
          <TableHead className="">Email</TableHead>
          <TableHead className="text-right">Invitar</TableHead>
        </TableRow>
      </TableHeader>
      <TableBody>
        {users.map((user) => (
          <TableRow key={user.id}>
            <TableCell>
              {user.firstName} {user.lastName}
            </TableCell>
            <TableCell>{user.email}</TableCell>
            <TableCell className="text-right">
              <SendInvitationButton email={user.email} />
            </TableCell>
          </TableRow>
        ))}
      </TableBody>
    </Table>
  );
};

export default SearchUsersTable;
